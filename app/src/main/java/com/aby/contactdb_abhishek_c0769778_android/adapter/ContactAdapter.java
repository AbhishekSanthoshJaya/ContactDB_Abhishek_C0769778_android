package com.aby.contactdb_abhishek_c0769778_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aby.contactdb_abhishek_c0769778_android.R;
import com.aby.contactdb_abhishek_c0769778_android.activities.AddContactActivity;
import com.aby.contactdb_abhishek_c0769778_android.database.ContactDB;
import com.aby.contactdb_abhishek_c0769778_android.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    List<Contact> personsList;
    Context context;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    public List<Contact> getPersonsList() {
        return personsList;
    }

    public void setPersonsList(List<Contact> personsList) {
        this.personsList = personsList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.contact_cell,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Contact currPerson = personsList.get(position);

        holder.name.setText(currPerson.getFirstname() + " " + currPerson.getLastname());
        holder.address.setText( currPerson.getAddress() );
        holder.phone.setText( currPerson.getPhone() );

        holder.mycardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), UpdateContactActivity.class);
//                intent.putExtra("data",personsList.get(position));
//                context.startActivity(intent);

            }
        });

        holder.deletePerson.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem( position );
            }
        } );

        holder.mycardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myintent = new Intent(context, AddContactActivity.class);
                myintent.putExtra("person", currPerson);

                context.startActivity(myintent);
                //  Toast.makeText(context,"position = "+position,Toast.LENGTH_LONG).show();

            }
        });

    }




    @Override
    public int getItemCount() {
        return personsList.size();
    }

    public void filterList(List<Contact> filteredList) {
        personsList =  filteredList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, address, phone;
        CardView mycardview;
        ImageView deletePerson;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mycardview = itemView.findViewById(R.id.newcardNote);
            name = itemView.findViewById( R.id.textView1 );
            address = itemView.findViewById( R.id.textView2 );
            phone = itemView.findViewById( R.id.textView3 );
            deletePerson = itemView.findViewById( R.id.delete_person );

        }
    }
    public void deleteItem(int position) {
        Contact person = personsList.get(position);
        ContactDB userDatabase = ContactDB.getInstance(getContext());
        userDatabase.daoObjct().delete(person);
        Toast.makeText(getContext(),"Deleted",Toast.LENGTH_SHORT).show();
        personsList.remove(position);
        notifyDataSetChanged();

    }
}
