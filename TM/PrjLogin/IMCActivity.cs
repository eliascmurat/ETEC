using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.Icu.Text;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;

namespace PrjLogin
{
    [Activity(Label = "IMCActivity", Theme = "@style/Theme.AppCompat.NoActionBar")]
    public class IMCActivity : Activity
    {
        EditText edtPeso, edtAltura;
        Button btnCalcular;
        RadioButton RdbMasculino;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            Xamarin.Essentials.Platform.Init(this, savedInstanceState);
            SetContentView(Resource.Layout.activity_imc);

            edtAltura = (EditText)FindViewById(Resource.Id.EdtAltura);
            edtPeso = (EditText)FindViewById(Resource.Id.EdtPeso);

            RdbMasculino = FindViewById<RadioButton>(Resource.Id.rdbMasculino);

            btnCalcular = (Button)FindViewById(Resource.Id.btnCalcular);

            btnCalcular.Click += BtnCalcular_Click;
        }
        
        private void BtnCalcular_Click(object sender, System.EventArgs e)
        {
            float IMC = float.Parse(edtPeso.Text) / (float.Parse(edtAltura.Text) * float.Parse(edtAltura.Text));

            DecimalFormat formato = new DecimalFormat("#.00");
            string sIMC = formato.Format(IMC);

            if (!RdbMasculino.Selected)
            {
                if (IMC > 39)
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.SetTitle("IMC: " + sIMC);
                    alertDialog.SetMessage("Obesidade Mórbida"); ;
                    alertDialog.SetNeutralButton("OK", delegate
                    {
                        alertDialog.Dispose();
                    });
                    alertDialog.Show();
                }
                else if (IMC >= 29 && IMC < 39)
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.SetTitle("IMC: " + sIMC);
                    alertDialog.SetMessage("Obesidade Moderada"); ;
                    alertDialog.SetNeutralButton("OK", delegate
                    {
                        alertDialog.Dispose();
                    });
                    alertDialog.Show();
                }
                else if (IMC >= 24 && IMC < 29)
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.SetTitle("IMC: " + sIMC);
                    alertDialog.SetMessage("Obesidade Leve"); ;
                    alertDialog.SetNeutralButton("OK", delegate
                    {
                        alertDialog.Dispose();
                    });
                    alertDialog.Show();
                }
                else if (IMC >= 19 && IMC < 24)
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.SetTitle("IMC: " + sIMC);
                    alertDialog.SetMessage("Normal"); ;
                    alertDialog.SetNeutralButton("OK", delegate
                    {
                        alertDialog.Dispose();
                    });
                    alertDialog.Show();
                }
                else if (IMC < 19) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.SetTitle("IMC: " + sIMC);
                    alertDialog.SetMessage("Abaixo do Normal"); ;
                    alertDialog.SetNeutralButton("OK", delegate
                    {
                        alertDialog.Dispose();
                    });
                    alertDialog.Show();
                }
            }
            else
            {
                if (IMC > 40)
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.SetTitle("IMC: " + sIMC);
                    alertDialog.SetMessage("Obesidade Mórbida"); ;
                    alertDialog.SetNeutralButton("OK", delegate
                    {
                        alertDialog.Dispose();
                    });
                    alertDialog.Show();
                }
                else if (IMC >= 30 && IMC < 40)
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.SetTitle("IMC: " + sIMC);
                    alertDialog.SetMessage("Obesidade Moderada"); ;
                    alertDialog.SetNeutralButton("OK", delegate
                    {
                        alertDialog.Dispose();
                    });
                    alertDialog.Show();
                }
                else if (IMC >= 25 && IMC < 30)
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.SetTitle("IMC: " + sIMC);
                    alertDialog.SetMessage("Obesidade Leve"); ;
                    alertDialog.SetNeutralButton("OK", delegate
                    {
                        alertDialog.Dispose();
                    });
                    alertDialog.Show();
                }
                else if (IMC >= 20 && IMC < 25)
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.SetTitle("IMC: " + sIMC);
                    alertDialog.SetMessage("Normal"); ;
                    alertDialog.SetNeutralButton("OK", delegate
                    {
                        alertDialog.Dispose();
                    });
                    alertDialog.Show();
                }
                else if (IMC < 20) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.SetTitle("IMC: " + sIMC);
                    alertDialog.SetMessage("Abaixo do Normal"); ;
                    alertDialog.SetNeutralButton("OK", delegate
                    {
                        alertDialog.Dispose();
                    });
                    alertDialog.Show();
                }
            }
        }
    }
}