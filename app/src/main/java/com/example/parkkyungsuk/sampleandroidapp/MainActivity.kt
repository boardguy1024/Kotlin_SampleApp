package com.example.parkkyungsuk.sampleandroidapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val initTextViewTranslationY = textView_progress.translationY

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView_progress.text = progress.toString()

                // 上の方向にアニメーションで移動させるため -1をかける
                val translationDistance = (initTextViewTranslationY + progress * resources.getDimension(R.dimen.text_anim_step) * -1)

                textView_progress.animate().translationY(translationDistance)

                // fromUserはUIでユーザーが直接移動したさいにTrueになる
                // 値を設定して呼ばれる際にはfalseになる
                // 同じ値を設定すると呼ばれない
                // Resetが必要な時のみボタンタップでアニメションさせる
                if (!fromUser) {

                    textView_progress.animate().setDuration(500).rotationBy(360f)
                            .translationY(initTextViewTranslationY)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        button_reset.setOnClickListener{ v ->
            seekBar.progress = 0
        }

    }
}
