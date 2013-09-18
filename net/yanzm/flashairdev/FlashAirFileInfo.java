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

import java.util.Calendar;

import android.text.format.DateFormat;

public class FlashAirFileInfo {

  public FlashAirFileInfo(String info, String dir) {
    int start;
    int end;

    start = info.lastIndexOf(",");
    int time = Integer.parseInt(info.substring(start + 1).trim());

    end = start;
    start = info.lastIndexOf(",", end - 1);
    int date = Integer.parseInt(info.substring(start + 1, end).trim());

    end = start;
    start = info.lastIndexOf(",", end - 1);
    mAttribute = Integer.parseInt(info.substring(start + 1, end).trim());

    end = start;
    start = info.lastIndexOf(",", end - 1);
    mSize = info.substring(start + 1, end);

    end = start;
    start = info.indexOf(",", dir.length());
    mFileName = info.substring(start + 1, end);

    mDir = dir;

    int year = ((date >> 9) & 0x0000007f) + 1980;
    int month = (date >> 5) & 0x0000000f - 1;
    int day = (date) & 0x0000001f;

    int hourOfDay = (time >> 11) & 0x0000001f;
    int minute = (time >> 5) & 0x0000003f;
    int second = ((time) & 0x0000001f) * 2;

    mCalendar = Calendar.getInstance();
    mCalendar.set(year, month, day, hourOfDay, minute, second);
  }

  public String mDir;
  public String mFileName;
  public String mSize;
  public int mAttribute;
  public Calendar mCalendar;

  public static final int ATTR_MASK_ARCHIVE = 0x00000020;
  public static final int ATTR_MASK_DIRECTORY = 0x00000010;
  public static final int ATTR_MASK_VOLUME = 0x00000008;
  public static final int ATTR_MASK_SYSTEM_FILE = 0x00000004;
  public static final int ATTR_MASK_HIDDEN_FILE = 0x00000002;
  public static final int ATTR_MASK_READ_ONLY = 0x00000001;

  public boolean isDirectory() {
    return (mAttribute & ATTR_MASK_DIRECTORY) > 0;
  }

  @Override
  public String toString() {
    return "DIR=" + mDir + " FILENAME=" + mFileName + " SIZE=" + mSize
        + " ATTRIBUTE=" + mAttribute + " DATE="
        + DateFormat.format("yyyy-MM-dd kk:mm:ss", mCalendar);
  }
}