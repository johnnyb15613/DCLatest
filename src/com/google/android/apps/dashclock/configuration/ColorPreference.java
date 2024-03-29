/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.apps.dashclock.configuration;

import net.nurik.roman.dashclock.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * A preference that allows the user to choose an application or shortcut.
 */
public class ColorPreference extends Preference {
    private static int[] COLOR_CHOICES = new int[]{
            0xff33b5e5,
            0xffaa66cc,
            0xff99cc00,
            0xffffbb33,
            0xffff4444,

            0xff0099cc,
            0xff9933cc,
            0xff669900,
            0xffff8800,
            0xffcc0000,

            0xffffffff,
    };

    private int mValue = 0;
    private Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
    private ImageView mPreviewView;

    public ColorPreference(Context context) {
        super(context);
        init();
    }

    public ColorPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setWidgetLayoutResource(R.layout.grid_item_color);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        mPreviewView = (ImageView) view.findViewById(R.id.color_view);
        setColorImageViewValue(mPreviewView, mValue);
    }

    public void setValue(int value) {
        if (callChangeListener(value)) {
            mValue = value;
            persistInt(value);
            notifyChanged();
        }
    }

    @Override
    protected void onClick() {
        super.onClick();

        ColorDialogFragment fragment = ColorDialogFragment.newInstance();
        fragment.setPreference(this);

        Activity activity = (Activity) getContext();
        activity.getFragmentManager().beginTransaction()
                .add(fragment, getFragmentTag())
                .commit();
    }

    @Override
    protected void onAttachedToActivity() {
        super.onAttachedToActivity();

        Activity activity = (Activity) getContext();
        ColorDialogFragment fragment = (ColorDialogFragment) activity
                .getFragmentManager().findFragmentByTag(getFragmentTag());
        if (fragment != null) {
            // re-bind preference to fragment
            fragment.setPreference(this);
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getInt(index, 0);
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        setValue(restoreValue ? getPersistedInt(0) : (Integer) defaultValue);
    }

    public String getFragmentTag() {
        return "color_" + getKey();
    }

    public int getValue() {
        return mValue;
    }

    public static class ColorDialogFragment extends DialogFragment {
        private ColorPreference mPreference;
        private ColorGridAdapter mAdapter;
        private GridView mColorGrid;

        public ColorDialogFragment() {
        }

        public static ColorDialogFragment newInstance() {
            return new ColorDialogFragment();
        }

        public void setPreference(ColorPreference preference) {
            mPreference = preference;
            tryBindLists();
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            tryBindLists();
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View rootView = layoutInflater.inflate(R.layout.dialog_colors, null);

            mColorGrid = (GridView) rootView.findViewById(R.id.color_grid);
            mColorGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> listView, View view,
                        int position, long itemId) {
                    mPreference.setValue((Integer) mAdapter.getItem(position));
                    dismiss();
                }
            });

            tryBindLists();

            return new AlertDialog.Builder(getActivity())
                    .setView(rootView)
                    .create();
        }

        private void tryBindLists() {
            if (mPreference == null) {
                return;
            }

            if (isAdded() && mAdapter == null) {
                mAdapter = new ColorGridAdapter();
            }

            if (mAdapter != null && mColorGrid != null) {
                mAdapter.setSelectedColor(mPreference.getValue());
                mColorGrid.setAdapter(mAdapter);
            }
        }

        private class ColorGridAdapter extends BaseAdapter {
            private List<Integer> mChoices = new ArrayList<Integer>();
            private int mSelectedColor;

            private ColorGridAdapter() {
                for (int color : COLOR_CHOICES) {
                    mChoices.add(color);
                }
            }

            @Override
            public int getCount() {
                return mChoices.size();
            }

            @Override
            public Object getItem(int position) {
                return mChoices.get(position);
            }

            @Override
            public long getItemId(int position) {
                return mChoices.get(position);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup container) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getActivity())
                            .inflate(R.layout.grid_item_color, container, false);
                }

                int color = mChoices.get(position);
                setColorImageViewValue((ImageView) convertView.findViewById(R.id.color_view),
                        color);
                convertView.setBackgroundColor(color == mSelectedColor
                        ? 0x6633b5e5 : 0);

                return convertView;
            }

            public void setSelectedColor(int selectedColor) {
                mSelectedColor = selectedColor;
                notifyDataSetChanged();
            }
        }
    }

    private static void setColorImageViewValue(ImageView imageView, int color) {
        Resources res = imageView.getContext().getResources();

        Drawable currentDrawable = imageView.getDrawable();
        GradientDrawable colorChoiceDrawable;
        if (currentDrawable != null && currentDrawable instanceof GradientDrawable) {
            // Reuse drawable
            colorChoiceDrawable = (GradientDrawable) currentDrawable;
        } else {
            colorChoiceDrawable = new GradientDrawable();
            colorChoiceDrawable.setShape(GradientDrawable.OVAL);
        }

        // Set stroke to dark version of color
        int darkenedColor = Color.rgb(
                Color.red(color) * 192 / 256,
                Color.green(color) * 192 / 256,
                Color.blue(color) * 192 / 256);

        colorChoiceDrawable.setColor(color);
        colorChoiceDrawable.setStroke((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1, res.getDisplayMetrics()), darkenedColor);
        imageView.setImageDrawable(colorChoiceDrawable);
    }
}
