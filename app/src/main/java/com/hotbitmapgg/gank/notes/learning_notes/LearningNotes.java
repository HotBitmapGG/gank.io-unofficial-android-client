package com.hotbitmapgg.gank.notes.learning_notes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LearningNotes extends RealmObject {

  private String chapter;

  @PrimaryKey
  private String url;

  private String type;


  public String getChapter() {

    return chapter;
  }


  public void setChapter(String chapter) {

    this.chapter = chapter;
  }


  public String getUrl() {

    return url;
  }


  public void setUrl(String url) {

    this.url = url;
  }


  public String getType() {

    return type;
  }


  public void setType(String type) {

    this.type = type;
  }
}
