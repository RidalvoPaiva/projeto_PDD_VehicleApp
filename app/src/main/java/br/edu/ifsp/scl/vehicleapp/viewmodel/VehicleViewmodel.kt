package br.edu.ifsp.scl.vehicleapp.viewmodel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.vehicleapp.adapter.VehicleListAdapter
import br.edu.ifsp.scl.vehicleapp.model.Vehicle
import br.edu.ifsp.scl.vehicleapp.repository.VehicleRepository
import kotlinx.coroutines.launch


// ViewModel responsável por gerenciar os veículos
class VehicleViewModel(private val repository: VehicleRepository) : ViewModel() {

    val vehicleData: Any
        get() {
            TODO()
        }
    val allVehicles: LiveData<List<Vehicle>> = repository.allVehicles.asLiveData()

    // Inserir novo veículo no banco de dados
    fun insert(vehicle: Vehicle) = viewModelScope.launch {
        repository.insert(vehicle)
    }

    // Atualizar veículo existente
    fun update(vehicle: Vehicle) = viewModelScope.launch {
        repository.update(vehicle)
    }

    // Deletar veículo
    fun delete(vehicle: Vehicle) = viewModelScope.launch {
        repository.delete(vehicle)
    }
}

// Factory para instanciar o ViewModel com o repositório necessário
class VehicleViewModelFactory(private val repository: VehicleRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VehicleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

// O fragmento  exibe a lista de veículos
@Suppress("CAST_NEVER_SUCCEEDS")
class VehicleListFragment : Fragment() {
    private val vehicleViewModel: VehicleViewModel by activityViewModels {
        VehicleViewModelFactory(
            (activity?.application as VehicleApplication).repository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentVehicleListBinding.inflate(inflater, container, false)

        // Configura o RecyclerView com o adaptador e o LayoutManager
        val adapter = VehicleListAdapter()
        adapter.also {
            it.also { it.also { binding.recyclerView.adapter = it } }
        }
        LinearLayoutManager(context).also {
            binding.recyclerView.layoutManager = it
        }

        // Observa a lista de veículos e atualiza o adaptador
        vehicleViewModel.allVehicles.observe(viewLifecycleOwner) { vehicles ->
            vehicles?.let { adapter.submitList(it) }
        }

        return binding.root
    }

    class VehicleApplication {

        val repository: VehicleRepository
            get() {
                TODO()
            }
    }

    // Fragmento para adicionar ou editar veículos
    @Suppress("CAST_NEVER_SUCCEEDS")
    class FragmentAddEditVehicle : Fragment() {
        private val vehicleViewModel: VehicleViewModel by activityViewModels {
            VehicleViewModelFactory((requireActivity().application as VehicleApplication).repository)
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            val binding =
                FragmentVehicleListBinding.inflate(inflater, container, false)
            binding.saveButton.setOnClickListener {
                val name = binding.nameEditText.text.toString()
                val model = binding.modelEditText.text.toString()
                val year = binding.yearEditText.text.toString().toInt()
                val vehicle = Vehicle(name = name, model = model, year = year)
                vehicleViewModel.insert(vehicle)
                findNavController().navigateUp()
            }

            return binding.root
        }
    }
}

