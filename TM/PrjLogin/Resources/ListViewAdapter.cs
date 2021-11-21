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
using PrjLogin.Resources.model;

namespace PrjLogin.Resources
{
    public class ViewHolder : Java.Lang.Object {
        public TextView txtBookName { get; set; }
        public TextView txtAuthor { get; set; }
        public TextView txtBookGender { get; set; }
    }
 
    public class ListViewAdapter:BaseAdapter
    {
        private Activity activity;
        private List<Book> lstBook;
        public ListViewAdapter(Activity activity, List<Book> lstBook) {
            this.activity = activity;
            this.lstBook = lstBook;
        }

        public override int Count {
            get {
                return lstBook.Count;
            }
        }

        public override Java.Lang.Object GetItem(int position)
        {
            return null;
        }

        public override long GetItemId(int position)
        {
            return lstBook[position].Id;
        }

        public override View GetView(int position, View convertView, ViewGroup parent)
        {
            var view = convertView ?? activity.LayoutInflater.Inflate(Resource.Layout.activity_crud2, parent, false);
            var txtBookName = view.FindViewById<TextView>(Resource.Id.txtBookName);
            var txtAuthor = view.FindViewById<TextView>(Resource.Id.txtAuthor);
            var txtBookGender = view.FindViewById<TextView>(Resource.Id.txtBookGender);

            txtBookName.Text = lstBook[position].BookName;
            txtAuthor.Text = lstBook[position].Author;
            txtBookGender.Text = lstBook[position].BookGender;

            return view;
        }
    }
}