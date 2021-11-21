using Android.App;
using Android.Content;
using Android.OS;
using Android.Widget;
using System;

namespace PrjLogin
{
    [Activity(Label = "LoginActivity", Theme = "@style/Theme.AppCompat.NoActionBar")]
    public class LoginActivity : Activity
    {
        TextView TxtLogin;
        Button BtnReturn;
        Button BtnAllApp;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);

            SetContentView(Resource.Layout.activity_login);
            TxtLogin = FindViewById<TextView>(Resource.Id.txtLogin);
            BtnReturn = FindViewById<Button>(Resource.Id.btnReturn);
            BtnAllApp = FindViewById<Button>(Resource.Id.btnAllapp);

            FindViewById<TextView>(Resource.Id.txtLogin).Text = TxtLogin.Text + Intent.GetStringExtra("username") ?? "Error getting data";

            BtnAllApp.Click += BtnAllApp_Click;

            BtnReturn.Click += delegate {
                this.Finish();
            };
        }
        private void BtnAllApp_Click(object sender, EventArgs e)
        {
            Intent nextActivity = new Intent(this, typeof(MenuActivity));
            StartActivity(nextActivity);
        }
    }
}
