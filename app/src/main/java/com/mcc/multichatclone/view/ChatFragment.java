package com.mcc.multichatclone.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.mcc.multichatclone.R;
import com.mcc.multichatclone.databinding.FragmentChatBinding;
import com.mcc.multichatclone.databinding.FragmentInizioBinding;
import com.mcc.multichatclone.viewcontroller.ChatItemAdapter;
import com.mcc.multichatclone.viewcontroller.ChatViewModel;
import com.mcc.multichatclone.viewcontroller.GruppiItemAdapter;
import com.mcc.multichatclone.viewcontroller.InizioViewModel;

public class ChatFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentChatBinding binding = FragmentChatBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        ChatViewModel chatModel = new ViewModelProvider(this).get(ChatViewModel.class);
        binding.setChatViewModel(chatModel);

        ChatItemAdapter adapter = new ChatItemAdapter();
        binding.chatList.setAdapter(adapter);

        chatModel.listaMessaggi.observe(getViewLifecycleOwner(), lista ->
        {
            adapter.setData(lista);
            binding.chatList.scrollToPosition(adapter.getItemCount() - 1);
        });

        // libera l'EditText field a button premuto
        chatModel.ricevutoMessaggio.observe(getViewLifecycleOwner(), (ricevuto) ->
        {
            if (!ricevuto.equals("false")) {
                if (ricevuto.equals("true")) {
                    //cancella il testo della TextView
                    //binding.messageText.getText().clear();
                    //scrolla la RecyclerView fino all'ultimo messaggio
                    binding.chatList.scrollToPosition(adapter.getItemCount() - 1);
                    chatModel.setRicevutoMessaggioFalse();
                }
                else {
                    Toast.makeText(binding.getRoot().getContext(), ricevuto, Toast.LENGTH_LONG).show();
                }
            }
        });

        final Observer<Boolean> osservaSeCancellareBarra = cancellaBarraMessaggio -> {
            if (cancellaBarraMessaggio == true) {
                binding.messageText.setText("");
            }
        };

        chatModel.cancellareBarraMessaggio.observe(getViewLifecycleOwner(), osservaSeCancellareBarra);

        return view;
    }

}