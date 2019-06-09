package me.zhengjie.domain;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author: 谢霜
 * @Date: 2018/07/27 下午 14:54
 * @Description:
 */
public class FileType {

    private FileType() {
    }

    private static String[] img = {"bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd", "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf"};
    private static String[] document = {"txt", "doc", "docx", "xls", "htm", "html", "jsp", "rtf", "wpd", "pdf", "ppt"};
    private static String[] video = {"mp4", "avi", "mov", "wmv", "asf", "navi", "3gp", "mkv", "f4v", "rmvb", "webm"};
    private static  String[] music = {"mp3", "wma", "wav", "mod", "ra", "cd", "md", "asf", "aac", "vqf", "ape", "mid", "ogg",
            "m4a", "vqf"};

    public static String fileType(String fileName) {
        if (fileName == null) {
            return null;
        } else {
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
            boolean index = ArrayUtils.contains(FileType.img, fileType);
            if (index) {
                return "img";
            }
            boolean index1 = ArrayUtils.contains(FileType.document, fileType);
            if (index1) {
                return "document";
            }
            boolean index2 = ArrayUtils.contains(FileType.video, fileType);
            if (index2) {
                return "video";
            }
            boolean index3 = ArrayUtils.contains(FileType.music, fileType);
            if (index3) {
                return "music";
            }
        }
        return "other";
    }
}