package com.gangbb.desigin.creational.simplefactory;

/**
 * @author Gangbb
 * @date 2021/3/27 20:49
 * @Description: 视频简单工厂
 */
public class VideoFactory {
    /**
     * 常规实现getVideo
     * @param type
     * @return
     */
//    public Video getVideo(String type){
//        if("java".equalsIgnoreCase(type)){
//            return new JavaVideo();
//        }else if ("python".equalsIgnoreCase(type)){
//            return new PythonVideo();
//        }
//        return null;
//    }

    /**
     * 反射实现getVideo
     * @param T
     * @return
     */
    public Video getVideo(Class T){
        Video video = null;
        try {
            video = (Video) Class.forName(T.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return video;
    }
}
