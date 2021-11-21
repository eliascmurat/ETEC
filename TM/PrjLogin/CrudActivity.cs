using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Util;
using Android.Views;
using Android.Widget;
using PrjLogin.Resources;
using PrjLogin.Resources.datahelper;
using PrjLogin.Resources.model;

namespace PrjLogin
{
    [Activity(Label = "CrudActivity", Theme = "@style/Theme.AppCompat.NoActionBar")]
    public class CrudActivity : Activity
    {
        DataBase db;
        ListView lstBook;
        List<Book> lstSource = new List<Book>();

        EditText edtAuthor;
        EditText edtBookName;
        EditText edtBookGender;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            SetContentView(Resource.Layout.activity_crud1);

            db = new DataBase();
            db.createDataBase();

            string folder = System.Environment.GetFolderPath(System.Environment.SpecialFolder.Personal);
            Log.Info("DB_PATH", folder);

            lstBook = FindViewById<ListView>(Resource.Id.lstBook);

            edtAuthor = FindViewById<EditText>(Resource.Id.edtAuthor);
            edtBookName = FindViewById<EditText>(Resource.Id.edtBookName);
            edtBookGender = FindViewById<EditText>(Resource.Id.edtBookGender);

            var btnAdd = FindViewById<Button>(Resource.Id.btnAdd);
            var btnEdit = FindViewById<Button>(Resource.Id.btnEdit);
            var btnDelete = FindViewById<Button>(Resource.Id.btnDelete);

            LoadData();

            btnAdd.Click += delegate
            {
                Book book = new Book()
                {
                    BookName = edtBookName.Text,
                    Author = edtAuthor.Text,
                    BookGender = edtBookGender.Text,
                };
                db.InsertBook(book);
                LoadData();
                cleanFields();
            };

            btnEdit.Click += delegate
            {
                Book book = new Book()
                {
                    Id = int.Parse(edtBookName.Tag.ToString()),
                    BookName = edtBookName.Text,
                    Author = edtAuthor.Text,
                    BookGender = edtBookGender.Text,
                };
                db.UpdateBook(book);
                LoadData();
                cleanFields();
            };

            btnDelete.Click += delegate
            {
                Book book = new Book()
                {
                    Id = int.Parse(edtBookName.Tag.ToString()),
                    BookName = edtBookName.Text,
                    Author = edtAuthor.Text,
                    BookGender = edtBookGender.Text,
                };
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.SetTitle("Attention: ");
                alertDialog.SetMessage("Do you really want to delete that record?"); ;
                alertDialog.SetNegativeButton("No", delegate
                {
                    alertDialog.Dispose();
                });
                alertDialog.SetPositiveButton("Yes", delegate
                {
                    alertDialog.Dispose();
                    db.DeleteBook(book);
                    LoadData();
                });
                alertDialog.Show();
                cleanFields();
            };

            lstBook.ItemClick += listView_ItemClick;

        }

        private void cleanFields() {
            edtAuthor.Text = "";
            edtBookName.Text = "";
            edtBookGender.Text = "";
        }

        private void listView_ItemClick(object sender, AdapterView.ItemClickEventArgs e)
        {
            var txtBookName = e.View.FindViewById<TextView>(Resource.Id.txtBookName);
            var txtAuthor = e.View.FindViewById<TextView>(Resource.Id.txtAuthor);
            var txtBookGender = e.View.FindViewById<TextView>(Resource.Id.txtBookGender);

            edtBookName.Text = txtBookName.Text;
            edtBookName.Tag = e.Id;
            edtAuthor.Text = txtAuthor.Text;
            edtBookGender.Text = txtBookGender.Text;
        }

        private void LoadData()
        {
            lstSource = db.SelectBook();
            var adapter = new ListViewAdapter(this, lstSource);
            lstBook.Adapter = adapter;
        }


    }
}