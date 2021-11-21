using System;
using System.Collections.Generic;
using System.IO;
using Android.App;
using Android.OS;
using Android.Widget;
using PrjLogin.Resources.model;
using SQLite;

namespace PrjLogin
{
    [Activity(Label = "RegisterActivity", Theme = "@style/Theme.AppCompat.NoActionBar")]
    public class RegisterActivity : Activity
    {
        EditText EdtUsername;
        EditText EdtPassword;
        EditText EdtPasswordConfirm;

        Button BtnSave;
        Button BtnReturn;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            SetContentView(Resource.Layout.activity_register);

            EdtUsername = FindViewById<EditText>(Resource.Id.edtUsername);
            EdtPassword = FindViewById<EditText>(Resource.Id.edtPassword);
            EdtPasswordConfirm = FindViewById<EditText>(Resource.Id.edtPasswordConfirm);

            BtnSave = FindViewById<Button>(Resource.Id.btnSave);
            BtnReturn = FindViewById<Button>(Resource.Id.btnReturn);

            BtnSave.Click += BtnSave_Click;
            BtnReturn.Click += delegate {
                this.Finish();
            };

        }

        public void CleanFields()
        {
            EdtUsername.Text = "";
            EdtPassword.Text = "";
            EdtPasswordConfirm.Text = "";
        }

        private void BtnSave_Click(object sender, System.EventArgs e)
        {
            try
            {
                string pass = EdtPassword.Text;
                string passC = EdtPasswordConfirm.Text;
                string name = EdtUsername.Text;
                
                if(pass == "" || passC == "" || name == ""){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.SetTitle("Warinig: ");
                    alertDialog.SetMessage("Empty fields are not allowed");
                    alertDialog.SetNeutralButton("OK", delegate
                    {
                        alertDialog.Dispose();
                    });
                    alertDialog.Show();
                }else{
                    if (pass == passC)
                    {
                        string dbPath = Path.Combine(System.Environment.GetFolderPath(System.Environment.SpecialFolder.Personal), "User.db3");
                        var db = new SQLiteConnection(dbPath);

                        db.CreateTable<Login>();

                        Login tblogin = new Login
                        {
                            Username = EdtUsername.Text,
                            Password = EdtPassword.Text
                        };

                        db.Insert(tblogin);

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.SetTitle("Sucess: ");
                        alertDialog.SetMessage("User successfully registered !"); ;
                        alertDialog.SetNeutralButton("OK", delegate
                        {
                            alertDialog.Dispose();
                        });
                        alertDialog.Show();
                        CleanFields();
                    }else{
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.SetTitle("Warning: ");
                        alertDialog.SetMessage("Different passwords"); ;
                        alertDialog.SetNeutralButton("OK", delegate
                        {
                            alertDialog.Dispose();
                        });
                        alertDialog.Show();
                        CleanFields();
                    }
                }
            }
            catch (Exception ex)
            {
                Toast.MakeText(this, ex.ToString(), ToastLength.Short).Show();
            }
        }
    }
}