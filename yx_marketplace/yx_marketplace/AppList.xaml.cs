using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using SQLite;
using yx_marketplace.Model;

namespace yx_marketplace
{

    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class AppList : ContentPage
    {
        public AppList()
        {
            InitializeComponent();
        }

        protected override void OnAppearing()
        {
            base.OnAppearing();

            using (SQLiteConnection conn = new SQLiteConnection(App.DatabaseLocation))
            {
                conn.CreateTable<CompanyInfo>();
                var info = conn.Table<CompanyInfo>().ToList();
                GameListView.ItemsSource = info;
            }

        }
    }
}
