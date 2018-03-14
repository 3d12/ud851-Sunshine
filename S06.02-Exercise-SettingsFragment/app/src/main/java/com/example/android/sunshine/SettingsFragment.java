package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceScreen;

import java.util.List;

/**
 * Created by ne on 3/14/18.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference_screen);
        PreferenceManager prefManager = this.getPreferenceManager();
        PreferenceScreen prefScreen = this.getPreferenceScreen();
        SharedPreferences sharedPreferences = prefManager.getSharedPreferences();
        int count = this.getPreferenceScreen().getPreferenceCount();
        for (int i = 0; i < count; i++) {
            Preference p = prefScreen.getPreference(i);
            if (p instanceof ListPreference) {
                ListPreference lp = (ListPreference) p;
                int valueIndex = lp.findIndexOfValue(lp.getValue());
                if (valueIndex >= 0) {
                    setPreferenceSummary(lp, lp.getEntries()[valueIndex]);
                }
            } else if (p instanceof EditTextPreference) {
                EditTextPreference etp = (EditTextPreference) p;
                String valueEntered = etp.getText();
                setPreferenceSummary(etp, valueEntered);
            }
        }
    }

    private void setPreferenceSummary(Preference preference, Object value) {
        if (value != null) {
            String valueString = (String) value;
            preference.setSummary(valueString);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_location_key))) {
            String newValue = sharedPreferences.getString(key, getString(R.string.pref_location_default));
            setPreferenceSummary(this.findPreference(key), newValue);
        } else if (key.equals(getString(R.string.pref_units_key))) {
            String newValue = sharedPreferences.getString(key, getString(R.string.pref_units_label_imperial));
            ListPreference lp = (ListPreference) this.findPreference(key);
            if (lp.findIndexOfValue(newValue) >= 0) {
                String newValueLabel = lp.getEntries()[lp.findIndexOfValue(newValue)].toString();
                setPreferenceSummary(lp, newValueLabel);
            } else {
                setPreferenceSummary(lp, "");
            }
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
