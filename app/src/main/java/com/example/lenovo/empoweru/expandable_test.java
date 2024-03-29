package com.example.lenovo.empoweru;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

public class expandable_test extends AppCompatActivity {
    ExpandableListView explvlist;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_test);
        explvlist=(ExpandableListView) findViewById(R.id.explv);
        explvlist.setAdapter(new ParentLevel());

    }

    public class ParentLevel extends BaseExpandableListAdapter
    {

        @Override
        public Object getChild(int arg0, int arg1)
        {
            return arg1;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition)
        {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,boolean isLastChild, View convertView, ViewGroup parent)
        {
            CustExpListview SecondLevelexplv = new CustExpListview(expandable_test.this);
            SecondLevelexplv.setAdapter(new SecondLevelAdapter());
            SecondLevelexplv.setGroupIndicator(null);
            return SecondLevelexplv;
        }

        @Override
        public int getChildrenCount(int groupPosition)
        {
            return 3;
        }

        @Override
        public Object getGroup(int groupPosition)
        {
            return groupPosition;
        }

        @Override
        public int getGroupCount()
        {
            return 5;
        }

        @Override
        public long getGroupId(int groupPosition)
        {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent)
        {
            TextView tv = new TextView(expandable_test.this);
            tv.setText("->FirstLevel");
            tv.setBackgroundColor(Color.BLUE);
            tv.setPadding(10, 7, 7, 7);

            return tv;
        }

        @Override
        public boolean hasStableIds()
        {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition)
        {
            return true;
        }
    }

    public class CustExpListview extends ExpandableListView
    {

        int intGroupPosition, intChildPosition, intGroupid;

        public CustExpListview(Context context)
        {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(960, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(600, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public class SecondLevelAdapter extends BaseExpandableListAdapter
    {

        @Override
        public Object getChild(int groupPosition, int childPosition)
        {
            return childPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition)
        {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent)
        {
            TextView tv = new TextView(expandable_test.this);
            tv.setText("child");
            tv.setPadding(15, 5, 5, 5);
            tv.setBackgroundColor(Color.YELLOW);
            tv.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
            return tv;
        }

        @Override
        public int getChildrenCount(int groupPosition)
        {
            return 5;
        }

        @Override
        public Object getGroup(int groupPosition)
        {
            return groupPosition;
        }

        @Override
        public int getGroupCount()
        {
            return 1;
        }

        @Override
        public long getGroupId(int groupPosition)
        {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
        {
            TextView tv = new TextView(expandable_test.this);
            tv.setText("-->Second Level");
            tv.setPadding(12, 7, 7, 7);
            tv.setBackgroundColor(Color.RED);
            return tv;
        }

        @Override
        public boolean hasStableIds()
        {
            // TODO Auto-generated method stub
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition)
        {
            // TODO Auto-generated method stub
            return true;
        }

    }
}
