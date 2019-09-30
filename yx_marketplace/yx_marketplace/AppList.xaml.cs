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

                var game = (from GameCount in info
                                 where GameCount.Categorystate == "game"
                                 select GameCount);
                GameListView.ItemsSource = game;
            }
        }
        private async void OnAppSelected(object sender ,ItemTappedEventArgs e)
        {
            var detail = e.Item as CompanyInfo;
            await Navigation.PushAsync(new MyListPageDetail(detail.AppName, detail.CompanyName, detail.Categorystate));
                 
        }
    }
}
