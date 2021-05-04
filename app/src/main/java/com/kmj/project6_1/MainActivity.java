package com.kmj.project6_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    //전역변수 선언
    Chronometer chrono; // 타이머 크로노미터 변수
    Button btnStart,btnEnd; //예약시작, 예약완료 변수
    RadioButton rdoCal,rdoTime;
    CalendarView calView; //날짜 설정 캘린더뷰 변수
    TimePicker tPicker; //시간 설정 타임피커 변수
    TextView tvYear,tvMonth,tvDay,tvHour,tvMiute; //년,월,일,시,분의 텍스트뷰 변수

    int selectYear,selectMonth,selectDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("시간 예약");

        //위젯 동기화
        //버튼
        btnStart = (Button)findViewById(R.id.btnStart);
        btnEnd=(Button)findViewById(R.id.btnEnd);

        //크로노미터
        chrono = (Chronometer)findViewById(R.id.chronometer1);

        //라디오버튼
        rdoCal = (RadioButton)findViewById(R.id.rdoCal);
        rdoTime=(RadioButton)findViewById(R.id.rdoTime);

        //FrameLayout의 2개의 위젯 cal , time
        tPicker = (TimePicker)findViewById(R.id.timePicker1);
        calView = (CalendarView)findViewById(R.id.calenderView1);

        //연,월,일,시,분 숫자 textView 연결
        tvYear = (TextView)findViewById(R.id.tvYear);
        tvMonth=(TextView)findViewById(R.id.tvMonth);
        tvDay=(TextView)findViewById(R.id.tvDay);
        tvMiute=(TextView)findViewById(R.id.tvMinute);
        tvHour=(TextView)findViewById(R.id.tvHour);



        //처음에는 타임피커, 캘린더뷰 둘다 안보이게 설정
        tPicker.setVisibility(View.INVISIBLE);
        calView.setVisibility(View.INVISIBLE);


        //라디오 버튼 중 '날짜 설정'이 눌리면 캘린더 보이게하기
        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.INVISIBLE);
                calView.setVisibility(View.VISIBLE);
            }
        });
        //라디오 버튼 중 '시간 설정'이 눌리면 타임피커 보이게하기
        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.VISIBLE);
                calView.setVisibility(View.INVISIBLE);
            }
        });

        //'예약시작'버튼 누르면 크로노미터 시작
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.setBase(SystemClock.elapsedRealtime()); //크로노미터 초기화
                chrono.start();
                chrono.setTextColor(Color.RED);
            }
        });

        //'예약완료'버튼 누르면 크로노미터를 정지하고 날짜, 시간을 가져옴
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.stop();
                chrono.setTextColor(Color.BLUE);

                tvYear.setText(Integer.toString(selectYear));
                tvMonth.setText(Integer.toString(selectMonth));
                tvDay.setText(Integer.toString(selectDay));
                tvHour.setText(Integer.toString(tPicker.getCurrentHour()));
                tvMiute.setText(Integer.toString(tPicker.getCurrentMinute()));
            }
        });

        //캘린더뷰에서 설정한 년,월,일 변수 selectYear, selectMonth, selectDay에 대입
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                selectYear=year;
                selectMonth=month+1;
                selectDay=dayOfMonth;
            }
        });

    }
}