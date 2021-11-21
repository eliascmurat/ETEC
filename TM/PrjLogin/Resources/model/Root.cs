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
using Newtonsoft.Json;

namespace PrjAllApp
{
    public class Root
    {
        public string cep { get; set; }
        public string logradouro { get; set; }
        public string complemento { get; set; }
        public string bairro { get; set; }
        public string localidade { get; set; }
        public string uf { get; set; }
        public string ibge { get; set; }
        public string gia { get; set; }
        public string ddd { get; set; }
        public string siafi { get; set; }

        public override string ToString()
        {
            return string.Format(
                "CEP: {0}\n" +
                "Logradouro: {1}\n" +
                "Complemento: {2}\n" +
                "Bairro: {3}\n" +
                "localidade: {4}\n" +
                "UF: {5}\n" +
                "IBGE: {6}\n" +
                "GIA: {7}\n" +
                "DDD: {8}\n" +
                "Siafi: {9}",
                cep, logradouro, complemento, bairro, localidade, uf, ibge, gia, ddd, siafi);
        }

    }
}