using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace yx_marketplace
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class MyListPageDetail : ContentPage
    {
        public MyListPageDetail(string AppName, string CompanyName, string Categorystate )
        {

            InitializeComponent();
            MyAppName.Text = AppName;
            MyCompanyName.Text = CompanyName;
            MyCategoryState.Text = Categorystate;
        }
    }
}