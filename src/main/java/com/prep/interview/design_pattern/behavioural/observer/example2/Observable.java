package com.prep.interview.design_pattern.behavioural.observer.example2;

public interface Observable {

     void attach(Observer observer);
     void detach(Observer observer) ;
     void setNum(int num);
     int getNum();
}
