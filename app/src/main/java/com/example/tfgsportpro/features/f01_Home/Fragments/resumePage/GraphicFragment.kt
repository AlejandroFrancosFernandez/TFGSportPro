package com.example.tfgsportpro.features.f01_Home.fragments.resumePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tfgsportpro.R
import com.example.tfgsportpro.databinding.FragmentGraphicBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class GraphicFragment : Fragment() {

    private var _binding: FragmentGraphicBinding? = null
    private val binding get() = _binding!!
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGraphicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupChartAppearance()
        loadChartData()

        // Regresa a la vista anterior
        binding.bComeback.setOnClickListener {
            it.isEnabled = false
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupChartAppearance() {
        val lineChart: LineChart = binding.lineChart
        lineChart.axisRight.isEnabled = false
        lineChart.description.isEnabled = false
        lineChart.legend.isEnabled = false

        lineChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            granularity = 1f
            isGranularityEnabled = true
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String =
                    value.toInt().toString()
            }
        }
        lineChart.axisLeft.apply {
            setDrawGridLines(false)
            granularity = 1f
            isGranularityEnabled = true
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String =
                    value.toInt().toString()
            }
        }
        lineChart.animateX(600)
    }

    private fun loadChartData() {
        val lineChart: LineChart = binding.lineChart
        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid ?: return

        firestore.collection("User")
            .document(currentUserUid)
            .collection("routines")
            .get()
            .addOnSuccessListener { result ->
                // 1) Conteo por fecha
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).apply {
                    timeZone = TimeZone.getTimeZone("Europe/Madrid")
                }
                val countByDate = mutableMapOf<String, Int>()
                result.documents.forEach { doc ->
                    val ts = doc.getLong("timestamp") ?: return@forEach
                    val key = sdf.format(Date(ts))
                    countByDate[key] = (countByDate[key] ?: 0) + 1
                }
                if (countByDate.isEmpty()) return@addOnSuccessListener

                // 2) Rango de fechas completo
                val dates = countByDate.keys.map { sdf.parse(it)!! }
                val minDate = dates.minOrNull()!!
                val maxDate = dates.maxOrNull()!!
                val allDates = mutableListOf<Date>()
                Calendar.getInstance().apply { time = minDate }.also { cal ->
                    while (!cal.time.after(maxDate)) {
                        allDates.add(cal.time)
                        cal.add(Calendar.DATE, 1)
                    }
                }

                // 3) Construir entries
                val entries = allDates.mapIndexed { idx, date ->
                    val cnt = countByDate[sdf.format(date)] ?: 0
                    Entry(idx.toFloat(), cnt.toFloat())
                }

                val dataSet = LineDataSet(entries, "").apply {
                    mode = LineDataSet.Mode.LINEAR
                    setDrawCircles(true)
                    circleRadius = 4f
                    setDrawCircleHole(false)
                    lineWidth = 2f
                    color = getColor(R.color.colorLetra)
                    setDrawValues(false)
                }

                // 4) Asignar al chart
                binding.lineChart.apply {
                    xAxis.labelCount = allDates.size
                    data = LineData(dataSet)
                    invalidate()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
