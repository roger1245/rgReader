package com.lj.rgreader.base;

public interface Book {
    /**
     * 获取标题
     * @return
     */
    String getTitle();

    /**
     * 获取作者
     * @return
     */
    String getAuthor();

    String getShortInform();

    String getId();

    String getCover();

}
