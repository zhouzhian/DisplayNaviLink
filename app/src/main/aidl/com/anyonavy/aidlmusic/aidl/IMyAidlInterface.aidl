// IMyAidlInterface.com.anyonavy.aidlmusic.aidl
package com.anyonavy.aidlmusic.aidl;

import com.anyonavy.aidlmusic.aidl.Music;
import com.anyonavy.aidlmusic.aidl.AIDLCallBack;

interface IMyAidlInterface {

    //切换歌曲/暂停/播放命令
    void sendCommand(String order);
    //获取当前播放状态
    Music getCurMusicInfo();
    //绑定监听
    void registerCallBack(AIDLCallBack cb);
    //解绑监听
    void unregisterCallBack(AIDLCallBack cb);

}
