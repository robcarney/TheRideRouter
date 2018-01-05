package cs4500.group202.controller.model;

/**
 * For the purposes of representing a file result for the view.
 */
public class FileResult {

  /**
   * Id of this file, respective to the others in the collection.
   */
  private int id;

  /**
   * Name for the file to be downloaded.
   */
  private String name;

  /**
   * Url for the file.
   */
  private String fileUrl;

  public FileResult(int id, String fileUrl, String name) {
    this.id = id;
    this.fileUrl = fileUrl;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFileUrl() {
    return fileUrl;
  }

  public void setFileUrl(String fileUrl) {
    this.fileUrl = fileUrl;
  }
}
