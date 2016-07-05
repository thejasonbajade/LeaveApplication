package com.orangeandbronze.leaveapp.domain;

import static java.time.Month.*;
import static java.time.MonthDay.*;

import java.time.*;

enum Holiday {
	NEW_YEAR(of(JANUARY, 1)), ARAW_NG_KAGITINGAN(of(APRIL, 9)), LABOR_DAY(
			of(MAY, 1)), INDEPENDENCE_DAY(of(JUNE, 1)), NATIONAL_HEROES_DAY(
			of(AUGUST, 30)), BONIFACIO_DAY(of(NOVEMBER, 30)), CHRISTMAS_EVE(
			of(DECEMBER, 24)), CHRISTMAS(of(DECEMBER, 25)), RIZAL_DAY(of(
			DECEMBER, 30)), NEW_YEARS_EVE(of(DECEMBER, 31));

	final MonthDay monthDay;

	Holiday(MonthDay monthDay) {
		this.monthDay = monthDay;
	}

	static boolean isHoliday(LocalDate localDate) {
		for (Holiday holiday : Holiday.values()) {
			if (MonthDay.from(localDate).equals(holiday.monthDay)) {
				return true;
			}
		}
		return false;
	}
}
