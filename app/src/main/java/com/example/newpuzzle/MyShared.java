package com.example.newpuzzle;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;

class MyShared {

    private static MyShared myShared;
    private static SharedPreferences pref;
    private int[] result = new int[3];

    private MyShared() {
    }

    public static MyShared getInstance(Context context) {
        if (pref == null) {
            pref = context.getSharedPreferences("Puzzle15", Context.MODE_PRIVATE);
        }
        return new MyShared();
    }


    public void saveMoveCount(int count) {
        result = getResults();
        if (result[2] > count) {
            result[2] = count;
            Arrays.sort(result);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(result[0]).append("#").append(result[1]).append("#").append(result[2]);
        pref.edit().putString("move_count", sb.toString()).apply();
    }

    public void save(int count) {
        int currentBestResult = pref.getInt("best", Integer.MAX_VALUE);
        if (count < currentBestResult) pref.edit().putInt("best", count).apply();
    }

    public int getBest() {
        return pref.getInt("best", Integer.MAX_VALUE);
    }

    public int[] getResults() {
        int[] arr = new int[3];
        if (!pref.getString("move_count", "").equals("")) {
            String[] s = pref.getString("move_count", "").split("#");
            for (int i = 0; i < 3; i++) {
                arr[i] = Integer.parseInt(s[i]);
            }
            result = arr;
        } else {
            result[0] = Integer.MAX_VALUE;
            result[1] = Integer.MAX_VALUE;
            result[2] = Integer.MAX_VALUE;
        }

        return result;
    }

    void cleaner() {
        pref.edit().clear().apply();
    }
}
