package com.example.webservicetugas;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class JadwalSholatAdapter extends RecyclerView.Adapter<JadwalSholatAdapter.CustomViewHolder> implements Filterable{
    private LayoutInflater mInflater;
    private ArrayList<JadwalSholatModel> JadwalSholat;
    private ArrayList<JadwalSholatModel> JadwalSholatFiltered;
    private Context context;

    public JadwalSholatAdapter(Context context, ArrayList<JadwalSholatModel> jadwalSholat) {
        this.context = context;
        this.JadwalSholat = jadwalSholat;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public JadwalSholatAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_jadwalsholat, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JadwalSholatAdapter.CustomViewHolder holder, int position) {
        JadwalSholatModel jadwalSholatModel = JadwalSholat.get(position);
        holder.tvKota.setText(jadwalSholatModel.getKota());
        holder.tvSubuh.setText(jadwalSholatModel.getSubuh());
        holder.tvDhuhur.setText(jadwalSholatModel.getDhuhur());
        holder.tvAshar.setText(jadwalSholatModel.getAshar());
        holder.tvMaghrib.setText(jadwalSholatModel.getMaghrib());
        holder.tvIsya.setText(jadwalSholatModel.getIsya());

    }

    @Override
    public int getItemCount() {
        return JadwalSholat.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();
                if (charString.isEmpty()) {
                    JadwalSholatFiltered = JadwalSholat;
                } else {
                    ArrayList<JadwalSholatModel> filteredList = new ArrayList<>();
                    for (JadwalSholatModel row : JadwalSholat) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getKota().toLowerCase().contains(charString)) {
                            filteredList.add(row);
                        }
                    }

                    JadwalSholatFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = JadwalSholatFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                JadwalSholatFiltered = (ArrayList<JadwalSholatModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView tvKota, tvSubuh, tvDhuhur, tvAshar, tvMaghrib, tvIsya;
        private CardView cv;

        public CustomViewHolder(View view) {
            super(view);
            tvKota = view.findViewById(R.id.tv_kota);
            tvSubuh = view.findViewById(R.id.tv_subuh);
            tvDhuhur = view.findViewById(R.id.tv_dhuhur);
            tvAshar = view.findViewById(R.id.tv_ashar);
            tvMaghrib = view.findViewById(R.id.tv_maghrib);
            tvIsya = view.findViewById(R.id.tv_isya);
        }
    }
}
