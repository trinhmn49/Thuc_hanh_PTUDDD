package com.example.donation10;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.donation10.databinding.FragmentFirstBinding;

//class for fragment_first.xml

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private int totalDonated = 0;

    //hàm khởi tạo giá trị của xml
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        //set giá trị cho amount picker
        binding.amountPicker.setMinValue(0);
        binding.amountPicker.setMaxValue(1000);
        //set giá trị cho progress bar
        binding.progressBar.setMax(10000);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //id của nút Button
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_Activity_report); //khi click vào nut bấm thì hiện fragment_second.xml
                Log.v("Donate", "Donate first Pressed");
                //giá trị của id amountPicker
                int amount = binding.amountPicker.getValue();
                int radioId = binding.paymentMethod.getCheckedRadioButtonId();
                String method = "";
                totalDonated += amount;
                binding.progressBar.setProgress(totalDonated);
                if (radioId == R.id.PayPal) {
                    method = "PayPal";
                }
                else {
                    method = "Direct";
                }
                Log.v("Donate", "Donate Pressed! with amount " + amount + ", method: " + method);
                Log.v("Donate", "Current total: " + totalDonated);
            }
        });
    }

    //huỷ giao diện
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}