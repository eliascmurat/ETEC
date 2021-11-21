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
using PrjLogin.Resources.model;
using SQLite;

namespace PrjLogin.Resources.datahelper
{
    public class DataBase {

        string folder = System.Environment.GetFolderPath(System.Environment.SpecialFolder.Personal);

        public bool createDataBase()
        {
            try
            {
                using (var connetion = new SQLiteConnection(System.IO.Path.Combine(folder, "Book.db"))) {
                    connetion.CreateTable<Book>();
                    return true;
                }
            }
            catch (SQLiteException ex)
            {
                Log.Info("SQLiteEx", ex.Message);
                return false;
            }
        }

        public bool InsertBook(Book book) {
            try
            {
                using (var connetion = new SQLiteConnection(System.IO.Path.Combine(folder, "Book.db")))
                {
                    connetion.Insert(book);
                    return true;
                }
            }
            catch (SQLiteException ex)
            {
                Log.Info("SQLiteEx", ex.Message);
                return false;
            }
        }

        public List<Book> SelectBook()
        {
            try
            {
                using (var connetion = new SQLiteConnection(System.IO.Path.Combine(folder, "Book.db")))
                {
                    return connetion.Table<Book>().ToList();
                }
            }
            catch (SQLiteException ex)
            {
                Log.Info("SQLiteEx", ex.Message);
                return null;
            }
        }

        public bool UpdateBook(Book book)
        {
            try
            {
                using (var connetion = new SQLiteConnection(System.IO.Path.Combine(folder, "Book.db")))
                {
                    connetion.Query<Book>("UPDATE Book set BookName=?, Author=?, BookGender=? WHERE Id=?", book.BookName, book.Author, book.BookGender, book.Id);
                    return true;
                }
            }
            catch (SQLiteException ex)
            {
                Log.Info("SQLiteEx", ex.Message);
                return false;
            }
        }

        public bool DeleteBook(Book book)
        {
            try
            {
                using (var connetion = new SQLiteConnection(System.IO.Path.Combine(folder, "Book.db")))
                {
                    connetion.Delete(book);
                    return true;
                }
            }
            catch (SQLiteException ex)
            {
                Log.Info("SQLiteEx", ex.Message);
                return false;
            }
        }

        public bool SelectOneBook(int Id)
        {
            try
            {
                using (var connetion = new SQLiteConnection(System.IO.Path.Combine(folder, "Book.db")))
                {
                    connetion.Query<Book>("SELECT * FROM Book WHERE Id=?", Id);
                    return true;
                }
            }
            catch (SQLiteException ex)
            {
                Log.Info("SQLiteEx", ex.Message);
                return false;
            }
        }
    }
}