package net.makslist.list.db;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import net.makslist.list.BR;

public class Lists extends BaseObservable {

  private int id;
  private String name;
  private String type;
  private String description;
  private Integer priority;

  public Lists() {
  }

  public Lists(int id, String name, String type, String description, int order) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.priority = order;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Bindable
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
    notifyPropertyChanged(BR.name);
  }

  @Bindable
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
    notifyPropertyChanged(BR.type);
  }

  @Bindable
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
    notifyPropertyChanged(BR.description);
  }

  @Bindable
  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
    notifyPropertyChanged(BR.priority);
  }

}
