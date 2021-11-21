using Android.App;
using Android.OS;
using Android.Support.V7.App;
using Android.Runtime;
using Android.Widget;
using System;
using System.IO;
using SQLite;
using AlertDialog = Android.App.AlertDialog;
using Android.Content;
using PrjLogin.Resources.model;

namespace PrjLogin
{
    [Activity(Label = "PrjAllApp", Theme = "@style/Theme.AppCompat.NoActionBar", MainLauncher = true)]
    public class MainActivity : AppCompatActivity
    {
        EditText EdtUsername;
        EditText EdtPassword;
        Button BtnLogin;
        Button BtnRegister;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            Xamarin.Essentials.Platform.Init(this, savedInstanceState);
            SetContentView(Resource.Layout.activity_main);

            EdtUsername = FindViewById<EditText>(Resource.Id.edtUsername);
            EdtPassword = FindViewById<EditText>(Resource.Id.edtPassword);
            BtnLogin = FindViewById<Button>(Resource.Id.btnLogin);
            BtnRegister = FindViewById<Button>(Resource.Id.btnRegister);

            BtnLogin.Click += BtnLogin_Click;
            BtnRegister.Click += BtnRegister_Click;

            CreateDataBase();
        }

        private void BtnLogin_Click(object sender, EventArgs e)
        {
            try
            {
                string dbPath = Path.Combine(System.Environment.GetFolderPath(System.Environment.SpecialFolder.Personal), "User.db3");
                var db = new SQLiteConnection(dbPath);
                var dados = db.Table<Login>();

                var login = dados.Where(x => x.Username == EdtUsername.Text && x.Password == EdtPassword.Text).FirstOrDefault();

                if (login != null)
                {
                    Toast.MakeText(this, "Login successfully", ToastLength.Short).Show();

                    var activityLogin = new Intent(this, typeof(LoginActivity));

                    activityLogin.PutExtra("username", FindViewById<EditText>(Resource.Id.edtUsername).Text);
                    StartActivity(activityLogin);
                }
                else
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.SetTitle("Warning: ");
                    alertDialog.SetMessage("Username and/or password is invalid"); ;
                    alertDialog.SetNeutralButton("OK", delegate
                    {
                        alertDialog.Dispose();
                    });
                    alertDialog.Show();
                }

            }
            catch (Exception ex)
            {
                Toast.MakeText(this, ex.ToString(), ToastLength.Short).Show();
            }
        }

        private void BtnRegister_Click(object sender, EventArgs e)
        {
            Intent nextActivity = new Intent(this, typeof(RegisterActivity));
            StartActivity(nextActivity); 
        }

        private void CreateDataBase()
        {
            try
            {
                string dbPath = Path.Combine(System.Environment.GetFolderPath(System.Environment.SpecialFolder.Personal), "User.db3");
                var db = new SQLiteConnection(dbPath);
            }
            catch (Exception ex)
            {
                Toast.MakeText(this, ex.ToString(), ToastLength.Short).Show();
            }
        }

        public override void OnRequestPermissionsResult(int requestCode, string[] permissions, [GeneratedEnum] Android.Content.PM.Permission[] grantResults)
        {
            Xamarin.Essentials.Platform.OnRequestPermissionsResult(requestCode, permissions, grantResults);

            base.OnRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}