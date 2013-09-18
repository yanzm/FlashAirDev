package com.example.flashairsample;

import java.util.List;

import junit.framework.TestCase;
import android.util.Log;

import com.example.flashairdev.FlashAirFileInfo;
import com.example.flashairdev.FlashAirUtils;

public class FlashAirDevTest extends TestCase {

  public static final String TAG = FlashAirDevTest.class.getSimpleName();

  public void testGetFileList() {
    String dir = "/DCIM";
    List<FlashAirFileInfo> list = FlashAirUtils.getFileList(dir);
    for (FlashAirFileInfo info : list) {
      Log.d(TAG, "FILE_INFO = " + info + " (" + dir + ")");
    }
    assertNotNull(list);
  }

  public void testGetFileCount() {
    String dir = "/DCIM";
    int count = FlashAirUtils.getFileCount(dir);
    Log.d(TAG, "FILE_COUNT = " + count + " (" + dir + ")");
    assertFalse(count == -1);
  }

  public void testGetFileCount2() {
    String dir = "/";
    int count = FlashAirUtils.getFileCount(dir);
    Log.d(TAG, "FILE_COUNT = " + count + " (" + dir + ")");
    assertFalse(count == -1);
  }

  public void testHasUpdate() {
    boolean hasUpdate = FlashAirUtils.hasUpdate();
    Log.d(TAG, "UPDATE = " + hasUpdate);
    assertNotNull(hasUpdate);
  }

  public void testGetSSID() {
    String ssid = FlashAirUtils.getSSID();
    Log.d(TAG, "SSID = " + ssid);
    assertNotNull(ssid);
  }

  public void testGetNetworkPassword() {
    String password = FlashAirUtils.getNetworkPassword();
    Log.d(TAG, "NETWORK_PASS = " + password);
    assertNotNull(password);
  }

  public void testGetMacAddress() {
    String address = FlashAirUtils.getMacAddress();
    Log.d(TAG, "MAC_ADDRESS = " + address);
    assertNotNull(address);
  }

  public void testGetBrowserLang() {
    String lang = FlashAirUtils.getBrowserLang();
    Log.d(TAG, "LANG = " + lang);
    assertNotNull(lang);
  }

  public void testGetFirmwareVersion() {
    String version = FlashAirUtils.getFirmwareVersion();
    Log.d(TAG, "FIRM_VER = " + version);
    assertNotNull(version);
  }

  public void testGetControlImagePath() {
    String path = FlashAirUtils.getControlImagePath();
    Log.d(TAG, "CIPATH = " + path);
    assertNotNull(path);
  }

  public void testGetAppMode() {
    int appMode = FlashAirUtils.getAppMode();
    Log.d(TAG, "APPMODE = " + appMode);
    assertFalse(appMode == -1);
  }

  public void testGetTimeOut() {
    long timeoutMillis = FlashAirUtils.getTimeoutMillis();
    Log.d(TAG, "APPAUTOTIME = " + timeoutMillis);
    assertFalse(timeoutMillis == -1);
  }

  public void testGetAppInfo() {
    String appInfo = FlashAirUtils.getAppInfo();
    Log.d(TAG, "APPINFO = " + appInfo);
    assertNotNull(appInfo);
  }

  public void testGetCID() {
    String cid = FlashAirUtils.getCardIdentifier();
    Log.d(TAG, "CID = " + cid);
    assertNotNull(cid);
  }

  public void testGetSize() {
    long[] size = FlashAirUtils.getSize();
    Log.d(TAG, "SIZE = " + size[0] + ", " + size[1]);
    assertNotNull(size);
  }

  public void testGetSizeWithUnit() {
    String[] size = FlashAirUtils.getSizeWithUnit();
    Log.d(TAG, "SIZE = " + size[0] + ", " + size[1]);
    assertNotNull(size);
  }
}
