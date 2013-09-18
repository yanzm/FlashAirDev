/*
* Copyright 2013 yanzm <anzai.y.aa@gmail.com>
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package net.yanzm.flashairdev;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.text.TextUtils;

public class FlashAirUtils {

  public static final String BASE = "http://flashair/";

  public static final String COMMAND = BASE + "command.cgi?";
  public static final String CONFIG = BASE + "config.cgi?";
  public static final String THUMBNAIL = BASE + "thumbnail.cgi?";
  public static final String UPLOAD = BASE + "upload.cgi?";

  public static final String FILE_LIST = COMMAND + "op=100&DIR=";
  public static final String FILE_COUNT = COMMAND + "op=101&DIR=";
  public static final String HAS_UPDATE = COMMAND + "op=102";
  public static final String GET_SSID = COMMAND + "op=104";
  public static final String GET_NETWORK_PASS = COMMAND + "op=105";
  public static final String GET_MAC_ADDRESS = COMMAND + "op=106";
  public static final String GET_BROWSER_LANG = COMMAND + "op=107";
  public static final String GET_FIRMWARE_VER = COMMAND + "op=108";
  public static final String GET_CONTROL_IMAGE_PATH = COMMAND + "op=109";
  public static final String GET_APPMODE = COMMAND + "op=110";
  public static final String GET_APPAUTOTIME = COMMAND + "op=111";
  public static final String GET_APPINFO = COMMAND + "op=117";
  public static final String GET_CID = COMMAND + "op=120";
  public static final String GET_SIZE = COMMAND + "op=140";

  public static Uri getFileUri(String dir, String fileName) {
    return Uri.parse(BASE + dir + "/" + fileName);
  }

  public static String getThumbnailUrl(String dir, String fileName) {
    return THUMBNAIL + dir + "/" + fileName;
  }

  /**
   * 指定したディレクトリ内のファイルリストを返す
   * 
   * ファームウェアバージョン1.00.00以上で利用可能
   * 
   * @param dir
   *          ディレクトリパス
   * @return ファイルリスト
   */
  public static List<FlashAirFileInfo> getFileList(String dir) {
    try {
      String result = Utils.accessToFlashAir(FILE_LIST + dir);
      if (TextUtils.isEmpty(result)) {
        return null;
      }

      ArrayList<FlashAirFileInfo> list = new ArrayList<FlashAirFileInfo>();
      for (String line : result.split("\n")) {
        if (TextUtils.isEmpty(line)) {
          continue;
        }
        if (line.split(",").length < 6) {
          continue;
        }
        FlashAirFileInfo info = new FlashAirFileInfo(line, dir);
        list.add(info);
      }
      return list;

    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * 指定したディレクトリ内のファイル数を返す
   * 
   * ファームウェアバージョン1.00.00以上で利用可能
   * 
   * @param dir
   *          ディレクトリパス
   * @return ファイル数
   */
  public static int getFileCount(String dir) {
    try {
      String result = Utils.accessToFlashAir(FILE_COUNT + dir);
      return Integer.parseInt(result);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return -1;
  }

  /**
   * FlashAirメモリがアップデートされているかどうかを返す
   * 
   * ファームウェアバージョン1.00.00以上で利用可能
   * 
   * @return アップデートされている場合 true
   */
  public static boolean hasUpdate() {
    try {
      String result = Utils.accessToFlashAir(HAS_UPDATE);
      return result.equals("1");
    } catch (IOException e) {
      e.printStackTrace();
    }

    return false;
  }

  /**
   * FlashAirのSSIDを返す
   * 
   * ファームウェアバージョン1.00.00以上で利用可能
   * 
   * @return 最大32文字
   */
  public static String getSSID() {
    try {
      return Utils.accessToFlashAir(GET_SSID);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * FlashAirがAPモードに設定されている場合のネットワークキーを返す
   * 
   * ファームウェアバージョン1.00.00以上で利用可能
   * 
   * @return 最大64桁の半角英数字
   */
  public static String getNetworkPassword() {
    try {
      return Utils.accessToFlashAir(GET_NETWORK_PASS);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * リクエストを発行したクライアントのMACアドレスを返す
   * 
   * ファームウェアバージョン1.00.00以上で利用可能
   * 
   * @return MACアドレス
   */
  public static String getMacAddress() {
    try {
      return Utils.accessToFlashAir(GET_MAC_ADDRESS);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * ブラウザの言語設定で利用可能な言語 (Accept-Languageパラメータ) を返す
   * 
   * ファームウェアバージョン1.00.00以上で利用可能
   * 
   * @return Accept-Languageパラメータ
   */
  public static String getBrowserLang() {
    try {
      return Utils.accessToFlashAir(GET_BROWSER_LANG);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * FlashAirのファームウェアのバージョンを返す
   * 
   * ファームウェアバージョン1.00.00以上で利用可能
   * 
   * @return ファームウェアバージョン
   */
  public static String getFirmwareVersion() {
    try {
      return Utils.accessToFlashAir(GET_FIRMWARE_VER);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * 無線起動画面として使う画像ファイルのフルパスを返す
   * 
   * ファームウェアバージョン2.00.00以上で利用可能
   * 
   * @return ファイルパス
   */
  public static String getControlImagePath() {
    try {
      return Utils.accessToFlashAir(GET_CONTROL_IMAGE_PATH);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * 無線LANモード(APPMODE)を返す
   * 
   * ファームウェアバージョン2.00.00以上で利用可能
   * 
   * @return APPMODE (0, 2, 4, 5)
   */
  public static int getAppMode() {
    try {
      String result = Utils.accessToFlashAir(GET_APPMODE);
      return Integer.parseInt(result);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return -1;
  }

  /**
   * 無線LANタイムアウト時間
   * 
   * ファームウェアバージョン2.00.00以上で利用可能
   * 
   * @return ミリ秒
   */
  public static long getTimeoutMillis() {
    try {
      String result = Utils.accessToFlashAir(GET_APPAUTOTIME);
      return Long.parseLong(result);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return -1;
  }

  /**
   * アプリケーション独自情報を返す
   * 
   * ファームウェアバージョン2.00.00以上で利用可能
   * 
   * @return 16ケタの英数字(APPINFO)
   */
  public static String getAppInfo() {
    try {
      return Utils.accessToFlashAir(GET_APPINFO);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * CID (Card Identification number register) を返す
   * 
   * ファームウェアバージョン1.00.03以上で利用可能
   * 
   * @return 32ケタの16進数
   */
  public static String getCardIdentifier() {
    try {
      String cid = Utils.accessToFlashAir(GET_CID);
      cid = cid.substring(0, 32);
      return cid;
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * 残り容量（バイト）と総容量（バイト）を返す
   * 
   * ファームウェアバージョン1.00.03以上で利用可能
   * 
   * @return 残り容量、総容量
   */
  public static long[] getSize() {
    try {
      String result = Utils.accessToFlashAir(GET_SIZE);
      String[] split = result.split(",");
      long sectorByte = Long.parseLong(split[1]);
      split = split[0].split("/");
      long remainSector = Long.parseLong(split[0]);
      long allSecotr = Long.parseLong(split[1]);
      return new long[] { remainSector * sectorByte, allSecotr * sectorByte };

    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * 残り容量（単位付きバイト）と総容量（単位付きバイト）を返す
   * 
   * @return 残り容量、総容量
   */
  public static String[] getSizeWithUnit() {
    long[] size = getSize();
    return new String[] { Utils.convertByteWithUnit(size[0]),
        Utils.convertByteWithUnit(size[1]) };
  }
}
