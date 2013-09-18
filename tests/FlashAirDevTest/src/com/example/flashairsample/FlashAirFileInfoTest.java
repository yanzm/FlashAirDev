package com.example.flashairsample;

import java.util.Calendar;

import com.example.flashairdev.FlashAirFileInfo;

import junit.framework.TestCase;

public class FlashAirFileInfoTest extends TestCase {

  public void testDate() {
    String dir = "/DCIM";
    String s = dir + ",IMG_20130706_133140.jpg,577089,32,17126,27658";
    FlashAirFileInfo info = new FlashAirFileInfo(s, dir);

    Calendar c = info.mCalendar;
    assertEquals(2013, c.get(Calendar.YEAR));
    assertEquals(7, c.get(Calendar.MONTH) + 1);
    assertEquals(6, c.get(Calendar.DATE));
    assertEquals(13, c.get(Calendar.HOUR_OF_DAY));
    assertEquals(32, c.get(Calendar.MINUTE));
    assertEquals(20, c.get(Calendar.SECOND));
  }

  public void testAttribute() {
    String dir = "/DCIM";
    String s = dir + ",IMG_20130706_133140.jpg,577089,32,17126,27658";
    FlashAirFileInfo info = new FlashAirFileInfo(s, dir);
    assertEquals(false, info.isDirectory());

    s = dir + ",100__TSB,0,16,17126,27658";
    info = new FlashAirFileInfo(s, dir);
    assertEquals(true, info.isDirectory());
  }
}
