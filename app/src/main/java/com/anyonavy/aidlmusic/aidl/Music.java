package com.anyonavy.aidlmusic.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zza on 2018/4/16.
 */

public class Music implements Parcelable{

    private String title;//歌曲标题
    private String path;//歌曲路径
    private long id;//id
    private String displayName;//文件名
    private int size;//歌曲的大小 单位：字节
    private int duration;//歌曲时长，单位：毫秒
    private String artist;//作者
    private String albumKey;//专辑信息的标识
    private String albumArt;//内置图片的路径

    public Music() {

    }

    public Music(String title, String path, long id, String displayName,
                 int size, int duration, String artist, String albumKey, String albumArt) {
        this.title = title;
        this.path = path;
        this.id = id;
        this.displayName = displayName;
        this.size = size;
        this.duration = duration;
        this.artist = artist;
        this.albumKey = albumKey;
        this.albumArt = albumArt;
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    @Override
    public String toString() {
        return "Music{" +
                "title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", id=" + id +
                ", displayName='" + displayName + '\'' +
                ", size=" + size +
                ", duration=" + duration +
                ", artist='" + artist + '\'' +
                ", albumKey='" + albumKey + '\'' +
                ", albumArt='" + albumArt + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbumKey() {
        return albumKey;
    }

    public void setAlbumKey(String albumKey) {
        this.albumKey = albumKey;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.path);
        dest.writeLong(this.id);
        dest.writeString(this.displayName);
        dest.writeInt(this.size);
        dest.writeInt(this.duration);
        dest.writeString(this.artist);
        dest.writeString(this.albumKey);
        dest.writeString(this.albumArt);
    }

    protected Music(Parcel in) {
        this.title = in.readString();
        this.path = in.readString();
        this.id = in.readLong();
        this.displayName = in.readString();
        this.size = in.readInt();
        this.duration = in.readInt();
        this.artist = in.readString();
        this.albumKey = in.readString();
        this.albumArt = in.readString();
    }

}
