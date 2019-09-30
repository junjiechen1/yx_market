using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using yx_marketplace.Model;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using SQLite;

namespace yx_marketplace
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class PortalPage : ContentPage
    {
        public PortalPage()
        {
            InitializeComponent();
        }

        private void portsave_click(object sender, EventArgs e)
        {
            if(Category.Text=="game" ||Category.Text == "education"|| Category.Text == "social")
            {
                CompanyInfo info = new CompanyInfo()
                {
                    AppName = Appname.Text,
                    CompanyName = Companyname.Text,
                    Categorystate = Category.Text

                };
                using (SQLiteConnection conn = new SQLiteConnection(App.DatabaseLocation))
                {
                    conn.CreateTable<CompanyInfo>();
                    int rows = conn.Insert(info);
                    if (rows > 0)
                        DisplayAlert("Success", "Information success inserted", "Ok");
                    else
                        DisplayAlert("Failure", "Information failure inserted", "Ok");
                }
            }
            else
            {
                DisplayAlert("Failure", "Information failure inserted", "Ok");
            }
            
        }
    }
}