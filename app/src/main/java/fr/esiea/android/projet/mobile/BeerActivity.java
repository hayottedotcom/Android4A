package fr.esiea.android.projet.mobile;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BeerActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_activity);

        BiersAdapter ba = new BiersAdapter();

        recyclerView = (RecyclerView)findViewById(R.id.rv_biere);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        IntentFilter intentFilter = new IntentFilter(BierUpdate.BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(ba),intentFilter);
        GetBiersServices.startActionGetAllBiers(this);

        recyclerView.setAdapter(ba);

    }

    public class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder> {

        JSONArray bieres;

        BiersAdapter() {
            this.bieres = getBiersFromFile();
        }

        @Override
        public BierHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(parent.getContext());
            return new BierHolder(li.inflate(R.layout.rv_bier_element, parent, false));
        }

        @Override
        public void onBindViewHolder(BierHolder holder, int position) {
            try {
                holder.name.setText(bieres.getJSONObject(position).getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return bieres.length();
        }

        public void setNewBiere() {
            this.bieres=getBiersFromFile();
            notifyDataSetChanged();
        }

        public class BierHolder extends RecyclerView.ViewHolder {
            private JSONArray biers;
            public TextView name;
            public BierHolder(View itemView) {
                super(itemView);

                    name = (TextView) itemView.findViewById(R.id.rv_bier_element_name);


            }
        }

        public JSONArray getBiersFromFile() {
            try {
                InputStream is = new FileInputStream(getCacheDir()+"/"+"bieres.json");
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                is.close();
                return new JSONArray(new String(buffer, "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
                return new JSONArray();
            } catch (JSONException e) {
                e.printStackTrace();
                return new JSONArray();
            }
        }


    }

}

