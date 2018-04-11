package com.jun.commonlib.utils

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {
    @SuppressLint("SimpleDateFormat")
    private val DEFAULT_FORMAT: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Milliseconds to the formatted time string.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param millis The milliseconds.
     * @return the formatted time string
     */
    fun millis2String(millis: Long): String {
        return millis2String(millis, DEFAULT_FORMAT);
    }

    /**
     * Milliseconds to the formatted time string.
     *
     * @param millis The milliseconds.
     * @param format The format.
     * @return the formatted time string
     */
    fun millis2String(millis: Long, format: DateFormat): String {
        return format.format(Date(millis));
    }

    /**
     * Formatted time string to the milliseconds.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return the milliseconds
     */
    fun string2Millis(time: String): Long {
        return string2Millis(time, DEFAULT_FORMAT);
    }

    /**
     * Formatted time string to the milliseconds.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return the milliseconds
     */
    fun string2Millis(time: String, format: DateFormat): Long {
        try {
            return format.parse(time).getTime();
        } catch (e: ParseException) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Formatted time string to the date.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return the date
     */
    fun string2Date(time: String): Date? {
        return string2Date(time, DEFAULT_FORMAT);
    }

    /**
     * Formatted time string to the date.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return the date
     */
    fun string2Date(time: String?, format: DateFormat): Date? {
        try {
            return format.parse(time);
        } catch (e: ParseException) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Date to the formatted time string.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param date The date.
     * @return the formatted time string
     */
    fun date2String(date: Date): String {
        return date2String(date, DEFAULT_FORMAT);
    }

    /**
     * Date to the formatted time string.
     *
     * @param date   The date.
     * @param format The format.
     * @return the formatted time string
     */
    fun date2String(date: Date, format: DateFormat): String {
        return format.format(date);
    }

    /**
     * Date to the milliseconds.
     *
     * @param date The date.
     * @return the milliseconds
     */
    fun date2Millis(date: Date): Long {
        return date.getTime();
    }

    /**
     * Milliseconds to the date.
     *
     * @param millis The milliseconds.
     * @return the date
     */
    fun millis2Date(millis: Long): Date {
        return Date(millis);
    }

    /**
     * Return the time span, in unit.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time1 The first formatted time string.
     * @param time2 The second formatted time string.
     * @param unit  The unit of time span.
     *              <ul>
     *              <li>{@link TimeConstants#MSEC}</li>
     *              <li>{@link TimeConstants#SEC }</li>
     *              <li>{@link TimeConstants#MIN }</li>
     *              <li>{@link TimeConstants#HOUR}</li>
     *              <li>{@link TimeConstants#DAY }</li>
     *              </ul>
     * @return the time span, in unit
     */
    fun getTimeSpan(time1: String,
                    time2: String,
                    @TimeConstants.Unit unit: Int): Long {
        return getTimeSpan(time1, time2, DEFAULT_FORMAT, unit)
    }

    /**
     * Return the time span, in unit.
     *
     * @param time1  The first formatted time string.
     * @param time2  The second formatted time string.
     * @param format The format.
     * @param unit   The unit of time span.
     *               <ul>
     *               <li>{@link TimeConstants#MSEC}</li>
     *               <li>{@link TimeConstants#SEC }</li>
     *               <li>{@link TimeConstants#MIN }</li>
     *               <li>{@link TimeConstants#HOUR}</li>
     *               <li>{@link TimeConstants#DAY }</li>
     *               </ul>
     * @return the time span, in unit
     */
    fun getTimeSpan(time1: String,
                    time2: String,
                    format: DateFormat,
                    @TimeConstants.Unit unit: Int): Long {
        return millis2TimeSpan(
                Math.abs(string2Millis(time1, format) - string2Millis(time2, format)), unit
        );
    }

    /**
     * Return the time span, in unit.
     *
     * @param date1 The first date.
     * @param date2 The second date.
     * @param unit  The unit of time span.
     *              <ul>
     *              <li>{@link TimeConstants#MSEC}</li>
     *              <li>{@link TimeConstants#SEC }</li>
     *              <li>{@link TimeConstants#MIN }</li>
     *              <li>{@link TimeConstants#HOUR}</li>
     *              <li>{@link TimeConstants#DAY }</li>
     *              </ul>
     * @return the time span, in unit
     */
    fun getTimeSpan(date1: Date,
                    date2: Date,
                    @TimeConstants.Unit unit: Int): Long {
        return millis2TimeSpan(Math.abs(date2Millis(date1) - date2Millis(date2)), unit)
    }

    /**
     * Return the time span, in unit.
     *
     * @param millis1 The first milliseconds.
     * @param millis2 The second milliseconds.
     * @param unit    The unit of time span.
     *                <ul>
     *                <li>{@link TimeConstants#MSEC}</li>
     *                <li>{@link TimeConstants#SEC }</li>
     *                <li>{@link TimeConstants#MIN }</li>
     *                <li>{@link TimeConstants#HOUR}</li>
     *                <li>{@link TimeConstants#DAY }</li>
     *                </ul>
     * @return the time span, in unit
     */
    fun getTimeSpan(millis1: Long,
                    millis2: Long,
                    @TimeConstants.Unit unit: Int): Long {
        return millis2TimeSpan(Math.abs(millis1 - millis2), unit);
    }

    /**
     * Return the fit time span.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time1     The first formatted time string.
     * @param time2     The second formatted time string.
     * @param precision The precision of time span.
     *                  <ul>
     *                  <li>precision = 0, return null</li>
     *                  <li>precision = 1, return 天</li>
     *                  <li>precision = 2, return 天, 小时</li>
     *                  <li>precision = 3, return 天, 小时, 分钟</li>
     *                  <li>precision = 4, return 天, 小时, 分钟, 秒</li>
     *                  <li>precision &gt;= 5，return 天, 小时, 分钟, 秒, 毫秒</li>
     *                  </ul>
     * @return the fit time span
     */
    fun getFitTimeSpan(time1: String,
                       time2: String,
                       precision: Int): String? {
        val delta = string2Millis(time1, DEFAULT_FORMAT) - string2Millis(time2, DEFAULT_FORMAT);
        return millis2FitTimeSpan(Math.abs(delta), precision);
    }

    /**
     * Return the fit time span.
     *
     * @param time1     The first formatted time string.
     * @param time2     The second formatted time string.
     * @param format    The format.
     * @param precision The precision of time span.
     *                  <ul>
     *                  <li>precision = 0, return null</li>
     *                  <li>precision = 1, return 天</li>
     *                  <li>precision = 2, return 天, 小时</li>
     *                  <li>precision = 3, return 天, 小时, 分钟</li>
     *                  <li>precision = 4, return 天, 小时, 分钟, 秒</li>
     *                  <li>precision &gt;= 5，return 天, 小时, 分钟, 秒, 毫秒</li>
     *                  </ul>
     * @return the fit time span
     */
    fun getFitTimeSpan(time1: String,
                       time2: String,
                       format: DateFormat,
                       precision: Int): String? {
        val delta = string2Millis(time1, format) - string2Millis(time2, format)
        return millis2FitTimeSpan(Math.abs(delta), precision);
    }

    /**
     * Return the fit time span.
     *
     * @param date1     The first date.
     * @param date2     The second date.
     * @param precision The precision of time span.
     *                  <ul>
     *                  <li>precision = 0, return null</li>
     *                  <li>precision = 1, return 天</li>
     *                  <li>precision = 2, return 天, 小时</li>
     *                  <li>precision = 3, return 天, 小时, 分钟</li>
     *                  <li>precision = 4, return 天, 小时, 分钟, 秒</li>
     *                  <li>precision &gt;= 5，return 天, 小时, 分钟, 秒, 毫秒</li>
     *                  </ul>
     * @return the fit time span
     */
    fun getFitTimeSpan(date1: Date, date2: Date, precision: Int): String? {
        return millis2FitTimeSpan(Math.abs(date2Millis(date1) - date2Millis(date2)), precision)
    }

    /**
     * Return the fit time span.
     *
     * @param millis1   The first milliseconds.
     * @param millis2   The second milliseconds.
     * @param precision The precision of time span.
     *                  <ul>
     *                  <li>precision = 0, return null</li>
     *                  <li>precision = 1, return 天</li>
     *                  <li>precision = 2, return 天, 小时</li>
     *                  <li>precision = 3, return 天, 小时, 分钟</li>
     *                  <li>precision = 4, return 天, 小时, 分钟, 秒</li>
     *                  <li>precision &gt;= 5，return 天, 小时, 分钟, 秒, 毫秒</li>
     *                  </ul>
     * @return the fit time span
     */
    fun getFitTimeSpan(millis1: Long,
                       millis2: Long,
                       precision: Int): String? {
        return millis2FitTimeSpan(Math.abs(millis1 - millis2), precision);
    }

    /**
     * Return the current time in milliseconds.
     *
     * @return the current time in milliseconds
     */
    fun getNowMills(): Long {
        return System.currentTimeMillis()
    }

    /**
     * Return the current formatted time string.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @return the current formatted time string
     */
    fun getNowString(): String {
        return millis2String(System.currentTimeMillis(), DEFAULT_FORMAT)
    }

    /**
     * Return the current formatted time string.
     *
     * @param format The format.
     * @return the current formatted time string
     */
    fun getNowString(format: DateFormat): String {
        return millis2String(System.currentTimeMillis(), format)
    }

    /**
     * Return the current date.
     *
     * @return the current date
     */
    fun getNowDate(): Date {
        return Date()
    }

    /**
     * Return the time span by now, in unit.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @param unit The unit of time span.
     *             <ul>
     *             <li>{@link TimeConstants#MSEC}</li>
     *             <li>{@link TimeConstants#SEC }</li>
     *             <li>{@link TimeConstants#MIN }</li>
     *             <li>{@link TimeConstants#HOUR}</li>
     *             <li>{@link TimeConstants#DAY }</li>
     *             </ul>
     * @return the time span by now, in unit
     */
    fun getTimeSpanByNow(time: String, @TimeConstants.Unit unit: Int): Long {
        return getTimeSpan(getNowString(), time, DEFAULT_FORMAT, unit);
    }

    /**
     * Return the time span by now, in unit.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @param unit   The unit of time span.
     *               <ul>
     *               <li>{@link TimeConstants#MSEC}</li>
     *               <li>{@link TimeConstants#SEC }</li>
     *               <li>{@link TimeConstants#MIN }</li>
     *               <li>{@link TimeConstants#HOUR}</li>
     *               <li>{@link TimeConstants#DAY }</li>
     *               </ul>
     * @return the time span by now, in unit
     */
    fun getTimeSpanByNow(time: String,
                         format: DateFormat,
                         @TimeConstants.Unit unit: Int): Long {
        return getTimeSpan(getNowString(format), time, format, unit);
    }

    /**
     * Return the time span by now, in unit.
     *
     * @param date The date.
     * @param unit The unit of time span.
     *             <ul>
     *             <li>{@link TimeConstants#MSEC}</li>
     *             <li>{@link TimeConstants#SEC }</li>
     *             <li>{@link TimeConstants#MIN }</li>
     *             <li>{@link TimeConstants#HOUR}</li>
     *             <li>{@link TimeConstants#DAY }</li>
     *             </ul>
     * @return the time span by now, in unit
     */
    fun getTimeSpanByNow(date: Date, @TimeConstants.Unit unit: Int): Long {
        return getTimeSpan(Date(), date, unit);
    }

    /**
     * Return the time span by now, in unit.
     *
     * @param millis The milliseconds.
     * @param unit   The unit of time span.
     *               <ul>
     *               <li>{@link TimeConstants#MSEC}</li>
     *               <li>{@link TimeConstants#SEC }</li>
     *               <li>{@link TimeConstants#MIN }</li>
     *               <li>{@link TimeConstants#HOUR}</li>
     *               <li>{@link TimeConstants#DAY }</li>
     *               </ul>
     * @return the time span by now, in unit
     */
    fun getTimeSpanByNow(millis: Long, @TimeConstants.Unit unit: Int): Long {
        return getTimeSpan(System.currentTimeMillis(), millis, unit);
    }

    /**
     * Return the fit time span by now.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time      The formatted time string.
     * @param precision The precision of time span.
     *                  <ul>
     *                  <li>precision = 0，返回 null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return the fit time span by now
     */
    fun getFitTimeSpanByNow(time: String, precision: Int): String? {
        return getFitTimeSpan(getNowString(), time, DEFAULT_FORMAT, precision);
    }

    /**
     * Return the fit time span by now.
     *
     * @param time      The formatted time string.
     * @param format    The format.
     * @param precision The precision of time span.
     *                  <ul>
     *                  <li>precision = 0，返回 null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return the fit time span by now
     */
    fun getFitTimeSpanByNow(time: String,
                            format: DateFormat,
                            precision: Int): String? {
        return getFitTimeSpan(getNowString(format), time, format, precision);
    }

    /**
     * Return the fit time span by now.
     *
     * @param date      The date.
     * @param precision The precision of time span.
     *                  <ul>
     *                  <li>precision = 0，返回 null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return the fit time span by now
     */
    fun getFitTimeSpanByNow(date: Date, precision: Int): String? {
        return getFitTimeSpan(getNowDate(), date, precision);
    }

    /**
     * Return the fit time span by now.
     *
     * @param millis    The milliseconds.
     * @param precision The precision of time span.
     *                  <ul>
     *                  <li>precision = 0，返回 null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return the fit time span by now
     */
    fun getFitTimeSpanByNow(millis: Long, precision: Int): String? {
        return getFitTimeSpan(System.currentTimeMillis(), millis, precision);
    }

    /**
     * Return the friendly time span by now.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return the friendly time span by now
     * <ul>
     * <li>如果小于 1 秒钟内，显示刚刚</li>
     * <li>如果在 1 分钟内，显示 XXX秒前</li>
     * <li>如果在 1 小时内，显示 XXX分钟前</li>
     * <li>如果在 1 小时外的今天内，显示今天15:32</li>
     * <li>如果是昨天的，显示昨天15:32</li>
     * <li>其余显示，2016-10-15</li>
     * <li>时间不合法的情况全部日期和时间信息，如星期六 十月 27 14:21:20 CST 2007</li>
     * </ul>
     */
    fun getFriendlyTimeSpanByNow(time: String): String {
        return getFriendlyTimeSpanByNow(time, DEFAULT_FORMAT);
    }

    /**
     * Return the friendly time span by now.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return the friendly time span by now
     * <ul>
     * <li>如果小于 1 秒钟内，显示刚刚</li>
     * <li>如果在 1 分钟内，显示 XXX秒前</li>
     * <li>如果在 1 小时内，显示 XXX分钟前</li>
     * <li>如果在 1 小时外的今天内，显示今天15:32</li>
     * <li>如果是昨天的，显示昨天15:32</li>
     * <li>其余显示，2016-10-15</li>
     * <li>时间不合法的情况全部日期和时间信息，如星期六 十月 27 14:21:20 CST 2007</li>
     * </ul>
     */
    fun getFriendlyTimeSpanByNow(time: String, format: DateFormat): String {
        return getFriendlyTimeSpanByNow(string2Millis(time, format));
    }

    /**
     * Return the friendly time span by now.
     *
     * @param date The date.
     * @return the friendly time span by now
     * <ul>
     * <li>如果小于 1 秒钟内，显示刚刚</li>
     * <li>如果在 1 分钟内，显示 XXX秒前</li>
     * <li>如果在 1 小时内，显示 XXX分钟前</li>
     * <li>如果在 1 小时外的今天内，显示今天15:32</li>
     * <li>如果是昨天的，显示昨天15:32</li>
     * <li>其余显示，2016-10-15</li>
     * <li>时间不合法的情况全部日期和时间信息，如星期六 十月 27 14:21:20 CST 2007</li>
     * </ul>
     */
    fun getFriendlyTimeSpanByNow(date: Date): String {
        return getFriendlyTimeSpanByNow(date.getTime());
    }

    /**
     * Return the friendly time span by now.
     *
     * @param millis The milliseconds.
     * @return the friendly time span by now
     * <ul>
     * <li>如果小于 1 秒钟内，显示刚刚</li>
     * <li>如果在 1 分钟内，显示 XXX秒前</li>
     * <li>如果在 1 小时内，显示 XXX分钟前</li>
     * <li>如果在 1 小时外的今天内，显示今天15:32</li>
     * <li>如果是昨天的，显示昨天15:32</li>
     * <li>其余显示，2016-10-15</li>
     * <li>时间不合法的情况全部日期和时间信息，如星期六 十月 27 14:21:20 CST 2007</li>
     * </ul>
     */
    fun getFriendlyTimeSpanByNow(millis: Long): String {
        val now = System.currentTimeMillis();
        val span = now - millis;
        if (span < 0)
        // U can read http://www.apihome.cn/api/java/Formatter.html to understand it.
            return String.format("%tc", millis);
        if (span < 1000) {
            return "刚刚";
        } else if (span < TimeConstants.MIN) {
            return String.format(Locale.getDefault(), "%d秒前", span / TimeConstants.SEC);
        } else if (span < TimeConstants.HOUR) {
            return String.format(Locale.getDefault(), "%d分钟前", span / TimeConstants.MIN);
        }
        // 获取当天 00:00
        val wee = getWeeOfToday();
        if (millis >= wee) {
            return String.format("今天%tR", millis);
        } else if (millis >= wee - TimeConstants.DAY) {
            return String.format("昨天%tR", millis);
        } else {
            return String.format("%tF", millis);
        }
    }

    fun getWeeOfToday(): Long {
        val cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * Return the milliseconds differ time span.
     *
     * @param millis   The milliseconds.
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the milliseconds differ time span
     */
    fun getMillis(millis: Long,
                  timeSpan: Long,
                  @TimeConstants.Unit unit: Int): Long {
        return millis + timeSpan2Millis(timeSpan, unit);
    }

    /**
     * Return the milliseconds differ time span.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time     The formatted time string.
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the milliseconds differ time span
     */
    fun getMillis(time: String,
                  timeSpan: Long,
                  @TimeConstants.Unit unit: Int): Long {
        return getMillis(time, DEFAULT_FORMAT, timeSpan, unit);
    }

    /**
     * Return the milliseconds differ time span.
     *
     * @param time     The formatted time string.
     * @param format   The format.
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the milliseconds differ time span.
     */
    fun getMillis(time: String,
                  format: DateFormat,
                  timeSpan: Long,
                  @TimeConstants.Unit unit: Int): Long {
        return string2Millis(time, format) + timeSpan2Millis(timeSpan, unit);
    }

    /**
     * Return the milliseconds differ time span.
     *
     * @param date     The date.
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the milliseconds differ time span.
     */
    fun getMillis(date: Date,
                  timeSpan: Long,
                  @TimeConstants.Unit unit: Int): Long {
        return date2Millis(date) + timeSpan2Millis(timeSpan, unit);
    }

    /**
     * Return the formatted time string differ time span.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param millis   The milliseconds.
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the formatted time string differ time span
     */
    fun getString(millis: Long,
                  timeSpan: Long,
                  @TimeConstants.Unit unit: Int): String {
        return getString(millis, DEFAULT_FORMAT, timeSpan, unit);
    }

    /**
     * Return the formatted time string differ time span.
     *
     * @param millis   The milliseconds.
     * @param format   The format.
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the formatted time string differ time span
     */
    fun getString(millis: Long,
                  format: DateFormat,
                  timeSpan: Long,
                  @TimeConstants.Unit unit: Int): String {
        return millis2String(millis + timeSpan2Millis(timeSpan, unit), format);
    }

    /**
     * Return the formatted time string differ time span.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time     The formatted time string.
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the formatted time string differ time span
     */
    fun getString(time: String,
                  timeSpan: Long,
                  @TimeConstants.Unit unit: Int): String {
        return getString(time, DEFAULT_FORMAT, timeSpan, unit);
    }

    /**
     * Return the formatted time string differ time span.
     *
     * @param time     The formatted time string.
     * @param format   The format.
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the formatted time string differ time span
     */
    fun getString(time: String,
                  format: DateFormat,
                  timeSpan: Long,
                  @TimeConstants.Unit unit: Int): String {
        return millis2String(string2Millis(time, format) + timeSpan2Millis(timeSpan, unit), format);
    }

    /**
     * Return the formatted time string differ time span.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param date     The date.
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the formatted time string differ time span
     */
    fun getString(date: Date,
                  timeSpan: Long,
                  @TimeConstants.Unit unit: Int): String {
        return getString(date, DEFAULT_FORMAT, timeSpan, unit);
    }

    /**
     * Return the formatted time string differ time span.
     *
     * @param date     The date.
     * @param format   The format.
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the formatted time string differ time span
     */
    fun getString(date: Date,
                  format: DateFormat,
                  timeSpan: Long,
                  @TimeConstants.Unit unit: Int): String {
        return millis2String(date2Millis(date) + timeSpan2Millis(timeSpan, unit), format);
    }

    /**
     * Return the date differ time span.
     *
     * @param millis   The milliseconds.
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the date differ time span
     */
    fun getDate(millis: Long,
                timeSpan: Long,
                @TimeConstants.Unit unit: Int): Date {
        return millis2Date(millis + timeSpan2Millis(timeSpan, unit));
    }

    /**
     * Return the date differ time span.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time     The formatted time string.
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the date differ time span
     */
    fun getDate(time: String,
                timeSpan: Long,
                @TimeConstants.Unit unit: Int): Date {
        return getDate(time, DEFAULT_FORMAT, timeSpan, unit);
    }

    /**
     * Return the date differ time span.
     *
     * @param time     The formatted time string.
     * @param format   The format.
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the date differ time span
     */
    fun getDate(time: String,
                format: DateFormat,
                timeSpan: Long,
                @TimeConstants.Unit unit: Int): Date {
        return millis2Date(string2Millis(time, format) + timeSpan2Millis(timeSpan, unit));
    }

    /**
     * Return the date differ time span.
     *
     * @param date     The date.
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the date differ time span
     */
    fun getDate(date: Date,
                timeSpan: Long,
                @TimeConstants.Unit unit: Int): Date {
        return millis2Date(date2Millis(date) + timeSpan2Millis(timeSpan, unit));
    }

    /**
     * Return the milliseconds differ time span by now.
     *
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the milliseconds differ time span by now
     */
    fun getMillisByNow(timeSpan: Long, @TimeConstants.Unit unit: Int): Long {
        return getMillis(getNowMills(), timeSpan, unit);
    }

    /**
     * Return the formatted time string differ time span by now.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the formatted time string differ time span by now
     */
    fun getStringByNow(timeSpan: Long, @TimeConstants.Unit unit: Int): String {
        return getStringByNow(timeSpan, DEFAULT_FORMAT, unit);
    }

    /**
     * Return the formatted time string differ time span by now.
     *
     * @param timeSpan The time span.
     * @param format   The format.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the formatted time string differ time span by now
     */
    fun getStringByNow(timeSpan: Long,
                       format: DateFormat,
                       @TimeConstants.Unit unit: Int): String {
        return getString(getNowMills(), format, timeSpan, unit);
    }

    /**
     * Return the date differ time span by now.
     *
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}</li>
     *                 <li>{@link TimeConstants#SEC }</li>
     *                 <li>{@link TimeConstants#MIN }</li>
     *                 <li>{@link TimeConstants#HOUR}</li>
     *                 <li>{@link TimeConstants#DAY }</li>
     *                 </ul>
     * @return the date differ time span by now
     */
    fun getDateByNow(timeSpan: Long, @TimeConstants.Unit unit: Int): Date {
        return getDate(getNowMills(), timeSpan, unit);
    }

    /**
     * Return whether it is today.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return {@code true}: yes<br>{@code false}: no
     */
    fun isToday(time: String): Boolean {
        return isToday(string2Millis(time, DEFAULT_FORMAT));
    }

    /**
     * Return whether it is today.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return {@code true}: yes<br>{@code false}: no
     */
    fun isToday(time: String, format: DateFormat): Boolean {
        return isToday(string2Millis(time, format));
    }

    /**
     * Return whether it is today.
     *
     * @param date The date.
     * @return {@code true}: yes<br>{@code false}: no
     */
    fun isToday(date: Date): Boolean {
        return isToday(date.getTime());
    }

    /**
     * Return whether it is today.
     *
     * @param millis The milliseconds.
     * @return {@code true}: yes<br>{@code false}: no
     */
    fun isToday(millis: Long): Boolean {
        val wee = getWeeOfToday();
        return millis >= wee && millis < wee + TimeConstants.DAY;
    }

    /**
     * Return whether it is leap year.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return {@code true}: yes<br>{@code false}: no
     */
    fun isLeapYear(time: String): Boolean {
        return isLeapYear(string2Date(time, DEFAULT_FORMAT));
    }

    /**
     * Return whether it is leap year.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return {@code true}: yes<br>{@code false}: no
     */
    fun isLeapYear(time: String, format: DateFormat): Boolean {
        return isLeapYear(string2Date(time, format));
    }

    /**
     * Return whether it is leap year.
     *
     * @param date The date.
     * @return {@code true}: yes<br>{@code false}: no
     */
    fun isLeapYear(date: Date?): Boolean {
        if (date == null) return false
        val cal = Calendar.getInstance()
        cal.setTime(date);
        val year = cal.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    /**
     * Return whether it is leap year.
     *
     * @param millis The milliseconds.
     * @return {@code true}: yes<br>{@code false}: no
     */
    fun isLeapYear(millis: Long): Boolean {
        return isLeapYear(millis2Date(millis));
    }

    /**
     * Return whether it is leap year.
     *
     * @param year The year.
     * @return {@code true}: yes<br>{@code false}: no
     */
    fun isLeapYear(year: Int): Boolean {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * Return the day of week in Chinese.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return the day of week in Chinese
     */
    fun getChineseWeek(time: String): String {
        return getChineseWeek(string2Date(time, DEFAULT_FORMAT));
    }

    /**
     * Return the day of week in Chinese.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return the day of week in Chinese
     */
    fun getChineseWeek(time: String, format: DateFormat): String {
        return getChineseWeek(string2Date(time, format));
    }

    /**
     * Return the day of week in Chinese.
     *
     * @param date The date.
     * @return the day of week in Chinese
     */
    fun getChineseWeek(date: Date?): String {
        if (date == null) return ""
        try {
            return SimpleDateFormat("E", Locale.CHINA).format(date);
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * Return the day of week in Chinese.
     *
     * @param millis The milliseconds.
     * @return the day of week in Chinese
     */
    fun getChineseWeek(millis: Long): String {
        return getChineseWeek(Date(millis));
    }

    /**
     * Return the day of week in US.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return the day of week in US
     */
    fun getUSWeek(time: String): String {
        return getUSWeek(string2Date(time, DEFAULT_FORMAT));
    }

    /**
     * Return the day of week in US.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return the day of week in US
     */
    fun getUSWeek(time: String, format: DateFormat): String {
        return getUSWeek(string2Date(time, format));
    }

    /**
     * Return the day of week in US.
     *
     * @param date The date.
     * @return the day of week in US
     */
    fun getUSWeek(date: Date?): String {
        if (date == null) return "";
        try {
            return SimpleDateFormat("EEEE", Locale.US).format(date);
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * Return the day of week in US.
     *
     * @param millis The milliseconds.
     * @return the day of week in US
     */
    fun getUSWeek(millis: Long): String {
        return getUSWeek(Date(millis));
    }

    /**
     * Return the number for indicating the day of the week.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return the number for indicating the day of the week
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
     */
    fun getWeekIndex(time: String): Int {
        return getWeekIndex(string2Date(time, DEFAULT_FORMAT));
    }

    /**
     * Return the number for indicating the day of the week.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return the number for indicating the day of the week
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
     */
    fun getWeekIndex(time: String, format: DateFormat): Int {
        return getWeekIndex(string2Date(time, format));
    }

    /**
     * Return the number for indicating the day of the week.
     *
     * @param date The date.
     * @return the number for indicating the day of the week
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
     */
    fun getWeekIndex(date: Date?): Int {
        if (date == null) return -1
        val cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Return the number for indicating the day of the week.
     *
     * @param millis The milliseconds.
     * @return the number for indicating the day of the week
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
     */
    fun getWeekIndex(millis: Long): Int {
        return getWeekIndex(millis2Date(millis));
    }

    /**
     * Return the number for indicating the week number within the current month.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return the number for indicating the week number within the current month
     */
    fun getWeekOfMonth(time: String): Int {
        return getWeekOfMonth(string2Date(time, DEFAULT_FORMAT));
    }

    /**
     * Return the number for indicating the week number within the current month.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return the number for indicating the week number within the current month
     */
    fun getWeekOfMonth(time: String, format: DateFormat): Int {
        return getWeekOfMonth(string2Date(time, format));
    }

    /**
     * Return the number for indicating the week number within the current month.
     *
     * @param date The date.
     * @return the number for indicating the week number within the current month
     */
    fun getWeekOfMonth(date: Date?): Int {
        if (date == null) return -1
        val cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * Return the number for indicating the week number within the current month.
     *
     * @param millis The milliseconds.
     * @return the number for indicating the week number within the current month
     */
    fun getWeekOfMonth(millis: Long): Int {
        return getWeekOfMonth(millis2Date(millis));
    }

    /**
     * Return the number for indicating the week number within the current year.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return the number for indicating the week number within the current year
     */
    fun getWeekOfYear(time: String): Int {
        return getWeekOfYear(string2Date(time, DEFAULT_FORMAT));
    }

    /**
     * Return the number for indicating the week number within the current year.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return the number for indicating the week number within the current year
     */
    fun getWeekOfYear(time: String, format: DateFormat): Int {
        return getWeekOfYear(string2Date(time, format));
    }

    /**
     * Return the number for indicating the week number within the current year.
     *
     * @param date The date.
     * @return the number for indicating the week number within the current year
     */
    fun getWeekOfYear(date: Date?): Int {
        if (date == null) return -1
        val cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Return the number for indicating the week number within the current year.
     *
     * @param millis The milliseconds.
     * @return the number for indicating the week number within the current year
     */
    fun getWeekOfYear(millis: Long): Int {
        return getWeekOfYear(millis2Date(millis));
    }

    val CHINESE_ZODIAC: Array<String> = arrayOf(
            "猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊");

    /**
     * Return the Chinese zodiac.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return the Chinese zodiac
     */
    fun getChineseZodiac(time: String?): String {
        if (time.isNullOrEmpty()) return ""
        return getChineseZodiac(string2Date(time, DEFAULT_FORMAT));
    }

    /**
     * Return the Chinese zodiac.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return the Chinese zodiac
     */
    fun getChineseZodiac(time: String, format: DateFormat): String {
        return getChineseZodiac(string2Date(time, format));
    }

    /**
     * Return the Chinese zodiac.
     *
     * @param date The date.
     * @return the Chinese zodiac
     */
    fun getChineseZodiac(date: Date?): String {
        if (date == null) return ""
        val cal = Calendar.getInstance();
        cal.setTime(date);
        return CHINESE_ZODIAC[cal.get(Calendar.YEAR) % 12];
    }

    /**
     * Return the Chinese zodiac.
     *
     * @param millis The milliseconds.
     * @return the Chinese zodiac
     */
    fun getChineseZodiac(millis: Long): String {
        return getChineseZodiac(millis2Date(millis))
    }

    /**
     * Return the Chinese zodiac.
     *
     * @param year The year.
     * @return the Chinese zodiac
     */
    fun getChineseZodiac(year: Int): String {
        return CHINESE_ZODIAC[year % 12]
    }

    val ZODIAC_FLAGS = arrayOf(
            20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22)
    val ZODIAC = arrayOf(
            "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座",
            "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"
    )

    /**
     * Return the zodiac.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return the zodiac
     */
    fun getZodiac(time: String): String {
        return getZodiac(string2Date(time, DEFAULT_FORMAT));
    }

    /**
     * Return the zodiac.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return the zodiac
     */
    fun getZodiac(time: String, format: DateFormat): String {
        return getZodiac(string2Date(time, format));
    }

    /**
     * Return the zodiac.
     *
     * @param date The date.
     * @return the zodiac
     */
    fun getZodiac(date: Date?): String {
        if (date == null) return ""
        val cal = Calendar.getInstance();
        cal.setTime(date);
        val month = cal.get(Calendar.MONTH) + 1;
        val day = cal.get(Calendar.DAY_OF_MONTH);
        return getZodiac(month, day);
    }

    /**
     * Return the zodiac.
     *
     * @param millis The milliseconds.
     * @return the zodiac
     */
    fun getZodiac(millis: Long): String {
        return getZodiac(millis2Date(millis));
    }

    /**
     * Return the zodiac.
     *
     * @param month The month.
     * @param day   The day.
     * @return the zodiac
     */
    fun getZodiac(month: Int, day: Int): String {
        return ZODIAC[if (day >= ZODIAC_FLAGS[month - 1]) {
            month - 1
        } else {
            (month + 10) % 12
        }];
    }

    fun timeSpan2Millis(timeSpan: Long, @TimeConstants.Unit unit: Int): Long {
        return timeSpan * unit;
    }

    fun millis2TimeSpan(millis: Long, @TimeConstants.Unit unit: Int): Long {
        return millis / unit;
    }

    fun millis2FitTimeSpan(millis: Long, precision: Int): String? {
        if (millis < 0 || precision <= 0) return null
        var tmillis = millis;
        val tprecision = Math.min(precision, 5)
        val units = arrayOf("天", "小时", "分钟", "秒", "毫秒")
        if (tmillis == 0L) return "0${units[precision - 1]}"
        val sb = StringBuilder()
        val unitLen = arrayOf(86400000, 3600000, 60000, 1000, 1);
        for (i in 0..tprecision) {
            if (tmillis >= unitLen[i]) {
                val mode = millis / unitLen[i];
                tmillis -= mode * unitLen[i];
                sb.append(mode).append(units[i]);
            }
        }
        return sb.toString();
    }
}