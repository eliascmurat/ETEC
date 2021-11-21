using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;

namespace PrjLogin
{
    [Activity(Label = "MenuActivity", Theme = "@style/Theme.AppCompat.NoActionBar")]
    public class MenuActivity : Activity
    {
        Button BtnImc;
        Button BtnGasEta;
        Button BtnViaCep;
        Button BtnMap;
        Button BtnCrud;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            Xamarin.Essentials.Platform.Init(this, savedInstanceState);
            SetContentView(Resource.Layout.activity_menu);

            BtnImc = FindViewById<Button>(Resource.Id.btnIMC);
            BtnGasEta= FindViewById<Button>(Resource.Id.btnGasEta);
            BtnViaCep = FindViewById<Button>(Resource.Id.btnViaCep);
            BtnMap = FindViewById<Button>(Resource.Id.btnMap);
            BtnCrud = FindViewById<Button>(Resource.Id.btnCrud);

            BtnImc.Click += BtnImc_Click;
            BtnGasEta.Click += BtnGasEta_Click;
            BtnViaCep.Click += BtnViaCep_Click;
            BtnMap.Click += BtnMap_Click;
            BtnCrud.Click += BtnCrud_Click;

        }

        private void BtnImc_Click(object sender, EventArgs e)
        {
            Intent nextActivity = new Intent(this, typeof(IMCActivity));
            StartActivity(nextActivity);
        }
        private void BtnGasEta_Click(object sender, EventArgs e)
        {
            Intent nextActivity = new Intent(this, typeof(CombustivelActivity));
            StartActivity(nextActivity);
        }
        private void BtnViaCep_Click(object sender, EventArgs e)
        {
            Intent nextActivity = new Intent(this, typeof(CepActivity));
            StartActivity(nextActivity);
        }
        private void BtnMap_Click(object sender, EventArgs e)
        {
            Intent nextActivity = new Intent(this, typeof(MapActivity));
            StartActivity(nextActivity);
        }
        private void BtnCrud_Click(object sender, EventArgs e)
        {
            Intent nextActivity = new Intent(this, typeof(CrudActivity));
            StartActivity(nextActivity);
        }
    }
}