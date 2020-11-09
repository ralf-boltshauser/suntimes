package com.example.suntimes

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class SmallSunTimes : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidgetSmall(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidgetSmall(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    val calculator = MainActivity.getSunCalculator(MainActivity.long, MainActivity.lat)
    val sunriseText = calculator.getOfficialSunriseForDate(Calendar.getInstance())
    val sunsetText = calculator.getOfficialSunsetForDate(Calendar.getInstance())
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.small_sun_times)
    views.setTextViewText(R.id.swidget_rise, sunriseText)
    views.setTextViewText(R.id.swidget_set, sunsetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}