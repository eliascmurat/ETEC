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
    [Activity(Label = "CombustivelActivity", Theme = "@style/Theme.AppCompat.NoActionBar")]
    public class CombustivelActivity : Activity
    {

        EditText edtGasolina, edtEtanol;
        Button btnCalcular;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            Xamarin.Essentials.Platform.Init(this, savedInstanceState);
            SetContentView(Resource.Layout.activity_combustivel);

            edtEtanol = (EditText)FindViewById(Resource.Id.EdtEtenol);
            edtGasolina = (EditText)FindViewById(Resource.Id.EdtGasolina);
            btnCalcular = (Button)FindViewById(Resource.Id.btnCalcular);
            btnCalcular.Click += BtnCalcular_Click;
        }

        private void BtnCalcular_Click(object sender, System.EventArgs e)
        {
            float etanol = float.Parse(edtEtanol.Text);
            float gasolina = float.Parse(edtGasolina.Text);
            float diferenca = etanol / gasolina;

            if (diferenca < 0.7)
            {
                //Etanol é melhor para abastecer
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.SetTitle("Resultado: ");
                alertDialog.SetMessage("Etenol é melhor para abastecer"); ;
                alertDialog.SetNeutralButton("OK", delegate
                {
                    alertDialog.Dispose();
                });
                alertDialog.Show();
            }
            else if (diferenca > 0.7)
            {
                //Gasolina é melhor para abastecer
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.SetTitle("Resultado: ");
                alertDialog.SetMessage("Gasolina é melhor para abastecer"); ;
                alertDialog.SetNeutralButton("OK", delegate
                {
                    alertDialog.Dispose();
                });
                alertDialog.Show();
            }
            else
            {
                //Tanto faz
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.SetTitle("Resultado: ");
                alertDialog.SetMessage("Tanto faz "); ;
                alertDialog.SetNeutralButton("OK", delegate
                {
                    alertDialog.Dispose();
                });
                alertDialog.Show();
            }
        }
    }
}