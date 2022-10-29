package com.feng.ceph.service;

import com.ceph.fs.CephMount;
import com.ceph.fs.CephNotDirectoryException;
import com.ceph.fs.CephStat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author f
 * @date 2022/10/24 22:30
 */
public class CephOperator {

    /** Ceph 的连接配置 */
    private CephMount mount;
    private String username;
    private String monIp;
    private String userKey;

    /**
     * 初始化 Ceph 的配置信息
     * @param username username
     * @param monIp    monIp
     * @param userKey  userKey
     * @param mountPath mountPath
     */
    public CephOperator(String username, String monIp, String userKey,String mountPath) {
        this.username = username;
        this.monIp = monIp;
        this.userKey = userKey;
        this.mount = new CephMount(username);
        this.mount.conf_set("key", userKey);
        mount.mount(mountPath);
    }

    /**
     * 查看目录列表
     * @param path path
     * @throws FileNotFoundException ex
     */
    public void listDir(String path) throws FileNotFoundException {
        String[] dirs = mount.listdir(path);
        System.out.println("contents of the dir: " + Arrays.asList(dirs));
    }

    /**
     * 新建目录
     * @param path path
     */
    public void mkDir(String path) throws IOException {
        mount.mkdirs(path, 0755);
    }

    /**
     * 删除目录
     * @param path path
     * @throws FileNotFoundException ex
     */
    public void delDir(String path) throws FileNotFoundException {
        mount.rmdir(path);
    }

    /**
     * 重命名目录 or 文件
     * @param oldName oldName
     * @param newName newName
     * @throws FileNotFoundException ex
     */
    public void renameDir(String oldName, String newName) throws FileNotFoundException {
        mount.rename(oldName, newName);
    }

    /**
     * 删除文件
     * @param path path
     * @throws FileNotFoundException ex
     */
    public void delFile(String path) throws FileNotFoundException {
        mount.unlink(path);
    }

    /**
     * 上传指定路径文件
     * @param filePath path
     * @param fileName name
     * @return
     */
    public Boolean uploadFileByPath(String filePath, String fileName) {
        if (null == this.mount) {
            return null;
        }

        char pathChar = File.separatorChar;
        String fileFullName = "";
        Long fileLength = 0L;
        Long uploadedLength = 0L;
        File file = null;

        FileInputStream fis = null;

        fileFullName = filePath + pathChar + fileName;
        file = new File(fileFullName);
        if (!file.exists()) {
            return false;
        }
        fileLength = file.length();

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] dirList = null;
        Boolean fileExist = false;
        try {
            dirList = this.mount.listdir("/");
            for (String fileInfo : dirList) {
                if (fileInfo.equals(fileName)) {
                    fileExist = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (!fileExist) {
            try {
                this.mount.open(fileName, CephMount.O_CREAT, 0);
                int fd = this.mount.open(fileName, CephMount.O_RDWR, 0);

                int length = 0;
                byte[] bytes = new byte[1024];
                while ((length = fis.read(bytes, 0, bytes.length)) != -1) {
                    // write
                    this.mount.write(fd, bytes, length, uploadedLength);
                    uploadedLength += length;

                    float rate = (float)uploadedLength * 100 / (float)fileLength;
                    String rateValue = (int)rate + "%";

                    if (uploadedLength == fileLength) {
                        break;
                    }
                }
                System.out.println("文件传输成功=====================================");
                this.mount.fchmod(fd, 0666);
                this.mount.close(fd);
                if (null != fis) {
                    fis.close();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (fileExist) {
            try {
                CephStat stat = new CephStat();
                this.mount.stat(fileName, stat);
                long lastLen = stat.size;
                int fd = this.mount.open(fileName, CephMount.O_RDWR, 0);

                int length = 0;
                byte[] bytes = new byte[1024];
                long uploadActLen = 0;
                while ((length = fis.read(bytes, 0, bytes.length)) != -1) {
                    this.mount.write(fd, bytes, length, lastLen);
                    uploadActLen += length;
                    float rate = (float)uploadActLen * 100 / (float)fileLength;
                    String rateValue = (int)rate + "%";
                    if (uploadActLen == fileLength) {
                        break;
                    }
                }
                System.out.println("====================================追加文件传输成功");
                this.mount.fchmod(fd, 0666);
                this.mount.close(fd);
                if (null != fis) {
                    fis.close();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return false;
    }

    /**
     * 设置工作目录
     * @param path path
     * @throws FileNotFoundException ex
     */
    public void setWorkDir(String path) throws FileNotFoundException {
        mount.chdir(path);
    }

    /**
     * 外部获取 mount
     * @return mount
     */
    public CephMount getMount() {
        return this.mount;
    }

    public void unmount() {
        mount.unmount();
    }
}
