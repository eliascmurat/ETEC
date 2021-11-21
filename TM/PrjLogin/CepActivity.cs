using System;
using Android.App;
using Android.OS;
using Android.Widget;
using Newtonsoft.Json;
using System.Net.Http;
using AlertDialog = Android.App.AlertDialog;
using PrjAllApp;

namespace PrjLogin
{
    [Activity(Label = "CepActivity", Theme = "@style/Theme.AppCompat.NoActionBar")]
    public class CepActivity : Activity
    {
        EditText EdtCep;
        Button BtnSearch;
        String cep;
        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            Xamarin.Essentials.Platform.Init(this, savedInstanceState);
            SetContentView(Resource.Layout.activity_cep);

            EdtCep = FindViewById<EditText>(Resource.Id.edtCep);
            BtnSearch = FindViewById<Button>(Resource.Id.btnSearch);

            BtnSearch.Click += async delegate
            {
                cep = EdtCep.Text;

                if (!string.IsNullOrEmpty(cep))
                {
                    var uri = "https://viacep.com.br/ws/" + cep + "/json/";
                    using (var client = new HttpClient())
                    {
                        try
                        {
                            var result = await client.GetStringAsync(uri);
                            var posts = JsonConvert.DeserializeObject<Root>(result);
                            String infos = "" + posts;

                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                            alertDialog.SetTitle("Sucess: ");
                            alertDialog.SetMessage(infos); ;
                            alertDialog.SetNeutralButton("OK", delegate
                            {
                                alertDialog.Dispose();
                            });
                            alertDialog.Show();
                        }
                        catch (Exception)
                        {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                            alertDialog.SetTitle("Error: ");
                            alertDialog.SetMessage("Please enter a valid CEP!"); ;
                            alertDialog.SetNeutralButton("OK", delegate
                            {
                                alertDialog.Dispose();
                            });
                            alertDialog.Show();
                        }
                    }
                }
                else
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.SetTitle("Error: ");
                    alertDialog.SetMessage("Please enter a valid CEP!"); ;
                    alertDialog.SetNeutralButton("OK", delegate
                    {
                        alertDialog.Dispose();
                    });
                    alertDialog.Show();
                }
            };
        }
    }
}