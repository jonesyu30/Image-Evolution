package me.jonesyu30.imageEvo.utils;

import java.time.Duration;
import java.time.Instant;

public class Timer {
	
	private Instant startTime, endTime;
	
	public Timer() {
	}
	public void start() {
		startTime = Instant.now();
	}
	public long getTime() {
		endTime = Instant.now();
		return Duration.between(startTime, endTime).toMillis();
	}
	public static void printTime(long x) {
		int seconds = (int) x / 1000;
		int hours = seconds / 3600;
		int minutes = (seconds % 3600) / 60;
		seconds = (seconds % 3600) % 60;
		System.out.printf("%d hours ", hours);
		System.out.printf("%d mintues ", minutes);
		System.out.printf("%d seconds \n", seconds);
	}
}
