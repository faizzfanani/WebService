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

public class ListSurahAdapter extends RecyclerView.Adapter<ListSurahAdapter.CustomViewHolder> implements Filterable{

    private LayoutInflater mInflater;
    private  ArrayList<ListSurahModel> ListBarang;
    private  ArrayList<ListSurahModel> ListBarangFull;
    private Context context;


    public ListSurahAdapter(Context context, ArrayList<ListSurahModel> listBarang) {
        this.context = context;
        this.ListBarang = listBarang;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_surah, parent, false);
        return new CustomViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        ListSurahModel listBarangModel = ListBarang.get(position);
        holder.tvNama.setText(listBarangModel.getNama());
        holder.tvNomor.setText(listBarangModel.getNomor());
        holder.tvArti.setText(listBarangModel.getArti());
    }

    @Override
    public int getItemCount() {
        return ListBarang.size() ;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvNomor, tvArti;
        private CardView cv;

        public CustomViewHolder(View itemView) {
            super(itemView);
            tvNama = (TextView) itemView.findViewById(R.id.tv_title);
            tvNomor = (TextView) itemView.findViewById(R.id.tv_produsen);
            tvArti = (TextView) itemView.findViewById(R.id.tv_noSertif);
        }
    }
    /*
    @Override
    public Filter getFilter() {
        return listFilter;
    }

    private Filter listFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String contactList = constraint.toString();
            if (contactList.isEmpty()) {
                ListBarangFull = ListBarang;
            } else {
                ArrayList<ListSurahModel> filteredList = new ArrayList<>();
                for (ListSurahModel row : ListBarang) {

                    // name match condition. this might differ depending on your requirement
                    // here we are looking for name or phone number match
                    if (row.getNama().toLowerCase().contains(contactList.toLowerCase()) || row.getArti().contains(contactList)) {
                        filteredList.add(row);
                    }
                }
                ListBarangFull = filteredList;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = ListBarangFull;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ListBarang =(ArrayList<ListSurahModel>) results.values;
            notifyDataSetChanged();
        }
    };*/
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<ListSurahModel> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0){
                    filteredList.addAll(ListBarangFull);
                }else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (ListSurahModel listBarangModel : ListBarangFull){
                        if(listBarangModel.getNama().toLowerCase().contains(filterPattern)){
                            filteredList.add(listBarangModel);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                ListBarang.clear();
                ListBarang.addAll((ArrayList)results.values);
                notifyDataSetChanged();
            }
        };
    }

}
