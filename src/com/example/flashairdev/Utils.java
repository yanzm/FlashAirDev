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

package com.example.flashairdev;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class Utils {

  public static String accessToFlashAir(String uri) throws IOException {
    URL url = new URL(uri);
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

    String result = null;
    try {
      InputStream in = new BufferedInputStream(urlConnection.getInputStream());
      result = inputStreamToString(in);
      in.close();
    } finally {
      urlConnection.disconnect();
    }

    return result;
  }

  private static String inputStreamToString(InputStream stream)
      throws IOException {
    Reader reader = new InputStreamReader(stream, "UTF-8");
    StringBuilder sb = new StringBuilder();
    char[] buffer = new char[1024];
    int num;
    while (0 < (num = reader.read(buffer))) {
      sb.append(buffer, 0, num);
    }
    return sb.toString();
  }

  public static String convertByteWithUnit(long b) {
    if (b > 1024 * 1024 * 1024) {
      return String.format(Locale.ENGLISH, "%.2fGB", (float) b
          / (1024 * 1024 * 1024));
    } else if (b > 1024 * 1024) {
      return String.format(Locale.ENGLISH, "%.2fMB", (float) b / (1024 * 1024));
    } else if (b > 1024) {
      return String.format(Locale.ENGLISH, "%.2fkB", (float) b / 1024);
    } else {
      return b + "byte";
    }
  }
}
