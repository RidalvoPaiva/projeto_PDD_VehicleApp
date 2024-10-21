package br.edu.ifsp.scl.vehicleapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.vehicleapp.model.Vehicle

private var Any.text: String
    get() {
        TODO("Not yet implemented")
    }
    set(value) {}

class VehicleListAdapter : RecyclerView.Adapter<VehicleListAdapter.VehicleViewHolder>() {
    // Inicializa a lista de veículos como uma lista vazia
    private var vehicleList: List<Vehicle> = emptyList<Vehicle>()

    // ViewHolder é responsável por armazenar as views de cada item
    class VehicleViewHolder(val binding: ItemVehicleBinding) : RecyclerView.ViewHolder(binding.root)

    // Infla o layout de cada item da RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val binding = ItemVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehicleViewHolder(binding)
    }

    class ItemVehicleBinding(val root: View) {
        val textViewName: Any
            get() {
                TODO()
            }

        companion object {
            fun inflate(
                from: LayoutInflater?,
                parent: ViewGroup,
                b: Boolean
            ): ItemVehicleBinding {
                TODO("Not yet implemented")
            }
        }

    }

    // Liga os dados do veículo à view do item
    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val currentVehicle = vehicleList[position]
        holder.binding.textViewName.text = currentVehicle.name  // Exibe o nome do veículo
    }

    // Retorna o número total de itens na lista
    override fun getItemCount(): Int = vehicleList.size

    /* Atualiza a lista de veículos exibidos e notifica o RecyclerView */
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Vehicle>) {
        vehicleList = list
        notifyDataSetChanged()  // Atualiza a RecyclerView
    }
}
