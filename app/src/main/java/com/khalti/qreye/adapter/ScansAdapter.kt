//package com.khalti.qreye.adapter
//
//import android.content.Context
//import android.database.Cursor
//import android.support.v4.content.ContextCompat
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.CursorAdapter
//import android.widget.TextView
//import java.text.DateFormat
//import java.text.ParseException
//import java.text.SimpleDateFormat
//import java.util.Locale
//
////class ScansAdapter(context: Context, cursor: Cursor) :
////	CursorAdapter(context, cursor, false) {
////	private val idIndex = cursor.getColumnIndex(Database.SCANS_ID)
////	private val timeIndex = cursor.getColumnIndex(Database.SCANS_DATETIME)
////	private val nameIndex = cursor.getColumnIndex(Database.SCANS_NAME)
////	private val contentIndex = cursor.getColumnIndex(Database.SCANS_CONTENT)
////	private val formatIndex = cursor.getColumnIndex(Database.SCANS_FORMAT)
////	private val selections = mutableMapOf<Long, Int>()
////	private val selectedColor = ContextCompat.getColor(
////		context, R.color.selected_row
////	)
////
////	fun select(view: View, id: Long, position: Int) {
////		view.select(
////			if (selections[id] == null) {
////				selections[id] = position
////				true
////			} else {
////				selections.remove(id)
////				false
////			}
////		)
////	}
////
////	fun clearSelection() {
////		selections.clear()
////	}
////
////	fun forSelection(callback: (Long, Int) -> Unit) {
////		selections.forEach {
////			callback.invoke(it.key, it.value)
////		}
////	}
////
////	fun getSelectedIds(): List<Long> = mutableListOf<Long>().apply {
////		forSelection { id, _ ->
////			add(id)
////		}
////	}
////
////	fun getSelectedContent(separator: String): String {
////		val sb = StringBuilder()
////		forSelection { _, position ->
////			getContent(position)?.let {
////				if (sb.isNotEmpty()) {
////					sb.append(separator)
////				}
////				sb.append(it)
////			}
////		}
////		return sb.toString()
////	}
////
////	fun getName(
////		position: Int
////	) = (getItem(position) as Cursor?)?.getString(nameIndex)
////
////	fun getContent(
////		position: Int
////	) = (getItem(position) as Cursor?)?.getString(contentIndex)
////
////	override fun newView(
////		context: Context,
////		cursor: Cursor,
////		parent: ViewGroup
////	): View? = LayoutInflater.from(parent.context).inflate(
////		R.layout.row_scan, parent, false
////	)
////
////	override fun bindView(
////		view: View,
////		context: Context,
////		cursor: Cursor
////	) {
////		getViewHolder(view).apply {
////			timeView.text = formatDateTime(cursor.getString(timeIndex))
////			val name = cursor.getString(nameIndex)
////			val content = cursor.getString(contentIndex)
////			var icon = 0
////			contentView.text = when {
////				name?.isNotEmpty() == true -> {
////					icon = R.drawable.ic_label
////					name
////				}
////
////				content.isNullOrEmpty() -> context.getString(
////					R.string.binary_data
////				)
////
////				else -> content
////			}
////			contentView.setCompoundDrawablesWithIntrinsicBounds(
////				icon, 0, 0, 0
////			)
////			formatView.text = prettifyFormatName(cursor.getString(formatIndex))
////		}
////		val selected = selections[cursor.getLong(idIndex)] != null
////		view.post {
////			// Needs to be put on the queue to work.
////			view.select(selected)
////		}
////	}
////
////	private fun View.select(selected: Boolean) {
////		setBackgroundColor(if (selected) selectedColor else 0)
////	}
////
////	private fun getViewHolder(
////		view: View
////	): ViewHolder = view.tag as ViewHolder? ?: ViewHolder(
////		view.findViewById(R.id.time),
////		view.findViewById(R.id.content),
////		view.findViewById(R.id.format)
////	).also {
////		view.tag = it
////	}
////
////	private data class ViewHolder(
////		val timeView: TextView,
////		val contentView: TextView,
////		val formatView: TextView
////	)
////}
////
////fun prettifyFormatName(format: String) = format.replace("_", " ")
////
////private fun formatDateTime(rfc: String): String {
////	try {
////		SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(rfc)?.let {
////			return DateFormat.getDateTimeInstance(
////				DateFormat.LONG,
////				DateFormat.SHORT
////			).format(it)
////		}
////	} catch (e: ParseException) {
////		// Ignore and fall through.
////	}
////	return rfc
////}
