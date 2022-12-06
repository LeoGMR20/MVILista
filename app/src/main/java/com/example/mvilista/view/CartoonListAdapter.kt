package com.example.mvilista.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvilista.R
import com.example.mvilista.api.CartoonService
import com.example.mvilista.model.Cartoon
import kotlinx.android.synthetic.main.cartoon_item.view.*

/*  1) Necesita heredar de la calse adpater
        pero Adapter de un RecyclerView
    2) El adaptador necesita obligatoriamente
        implementar el patron ViewHolder
    3) La calse interna ViewHolder que creaas
        debe heredar o extender de la clase
        abstracta de RecyclerView
        View Holder
    4) Los métodos a implemntar refieren
        mucho al tratamiento con el ViewHolder
*/
class CartoonListAdapter(private val cartoons: ArrayList<Cartoon>):
RecyclerView.Adapter<CartoonListAdapter.DataViewHolder>(){

    //Método forzado para explicar las actualizaciones del adapter
    fun newCartoons(newCartoons: List<Cartoon>) {
        cartoons.clear()
        cartoons.addAll(newCartoons)
        //método noticfica de cambios al adaptador para que redibuje o actualica sus items
        notifyDataSetChanged()
    }
    //Patrón ViewHolder para el adapter
    //necesitas la referencia de la View o Layout
    //que se supone vas a mapear
    //ncesitas heredar de la clase padre ViewHolder
    class DataViewHolder(itemView: View):
    RecyclerView.ViewHolder(itemView) {
        fun bind(cartoon: Cartoon) {
            itemView.tvName.text = cartoon.name
            itemView.tvLocation.text = cartoon.lastLocation
            val url = CartoonService.BASE_URL + cartoon.image
            Glide.with(itemView.context)
                .load(url)
                .into(itemView.ivCartoon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
        viewType: Int): DataViewHolder = DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cartoon_item,
                parent
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder,
        position: Int) {
        holder.bind(cartoons[position])
    }

    override fun getItemCount(): Int = cartoons.size
}