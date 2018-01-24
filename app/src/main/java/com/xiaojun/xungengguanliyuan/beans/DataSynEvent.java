package com.xiaojun.xungengguanliyuan.beans;

/**
 * Created by Administrator on 2018/1/23.
 */

public class DataSynEvent {

    private String video_uri;
    private String output_directory;
    private String video_screenshot;

    public String getVideo_uri() {
        return video_uri;
    }

    public void setVideo_uri(String video_uri) {
        this.video_uri = video_uri;
    }

    public String getOutput_directory() {
        return output_directory;
    }

    public void setOutput_directory(String output_directory) {
        this.output_directory = output_directory;
    }

    public String getVideo_screenshot() {
        return video_screenshot;
    }

    public void setVideo_screenshot(String video_screenshot) {
        this.video_screenshot = video_screenshot;
    }

    public DataSynEvent(String video_uri, String output_directory, String video_screenshot) {
        this.video_uri = video_uri;
        this.output_directory = output_directory;
        this.video_screenshot = video_screenshot;
    }

    @Override
    public String toString() {
        return "DataSynEvent{" +
                "video_uri='" + video_uri + '\'' +
                ", output_directory='" + output_directory + '\'' +
                ", video_screenshot='" + video_screenshot + '\'' +
                '}';
    }
}
