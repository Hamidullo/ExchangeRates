package pdp.android.homeworktwo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pdp.android.homeworktwo.R
import pdp.android.homeworktwo.databinding.ItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import pdp.android.homeworktwo.model.Data

class DataAdapter(var listData: List<Data>, var context: Context):
    RecyclerView.Adapter<DataAdapter.VH>() {

    inner class VH(var itemDataBinding: ItemBinding): RecyclerView.ViewHolder(itemDataBinding.root),View.OnClickListener{

        private lateinit var mListener: ItemClickListener

        fun setClickListener(listener: ItemClickListener?) {
            if (listener != null) {
                mListener = listener
            }
        }

        fun onBinding(data: Data){
            itemDataBinding.root.setOnClickListener(this)
            itemDataBinding.nameItem.text = data.ccyNmUZ
            itemDataBinding.rateItem.text = data.rate
            if (data.diff.startsWith("-")){
                itemDataBinding.diffItem.text = data.diff
                itemDataBinding.diffItem.setCompoundDrawablesWithIntrinsicBounds(R.drawable.decline,0,0,0)

            } else {
                itemDataBinding.diffItem.setCompoundDrawablesWithIntrinsicBounds(R.drawable.height,0,0,0)
                itemDataBinding.diffItem.text = data.diff
            }
        }

        override fun onClick(p0: View?) {
            mListener.onClickItem(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBinding(listData[position])

        holder.setClickListener(object : ItemClickListener {
            override fun onClickItem(pos: Int) {

                val bt = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
                val view: View = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_lay, null)
                val rateTextView = view.findViewById<TextView>(R.id.rateBottom)
                val diffTextView = view.findViewById<TextView>(R.id.diffTextBottom)
                val diffImageView = view.findViewById<ImageView>(R.id.diffIconBottom)
                val uzTextView = view.findViewById<TextView>(R.id.uzBottom)
                val rusTextView = view.findViewById<TextView>(R.id.rusBottom)
                val usdTextView = view.findViewById<TextView>(R.id.usdBottom)
                val dateTextView = view.findViewById<TextView>(R.id.dateBottom)

                rateTextView.text = listData[pos].rate
                if (listData[pos].diff.startsWith("-")){
                    diffImageView.setImageDrawable(context.getDrawable(R.drawable.decline))
                    diffTextView.text = listData[pos].diff
                } else {
                    diffImageView.setImageDrawable(context.getDrawable(R.drawable.height))
                    diffTextView.text = listData[pos].diff
                }
                uzTextView.text = listData[pos].ccyNmUZ
                rusTextView.text = listData[pos].ccyNmRU
                usdTextView.text = listData[pos].ccyNmEN
                dateTextView.text = listData[pos].date

                bt.setContentView(view)
                bt.show()
            }

        })
    }

    override fun getItemCount(): Int = listData.size

    interface ItemClickListener {
        fun onClickItem(pos: Int)
    }
}