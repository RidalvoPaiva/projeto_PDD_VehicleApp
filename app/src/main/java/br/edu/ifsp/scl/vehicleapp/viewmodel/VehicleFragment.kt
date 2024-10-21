package br.edu.ifsp.scl.vehicleapp.viewmodel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels


class VehicleFragment : Fragment() {


    private val vehicleViewModel: VehicleViewModel by viewModels()
    private var _binding: FragmentVehiclelistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inicializa o ViewBinding
        _binding = FragmentVehiclelistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Exemplo de observação dos dados do ViewModel
        vehicleViewModel.vehicleData.observe(viewLifecycleOwner) { vehicles ->
            // Atualiza a UI com os dados dos veículos
            binding.vehicleListRecyclerView.adapter = VehicleAdapter(vehicles)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Libera o binding para evitar vazamento de memória
        _binding = null
    }
}







