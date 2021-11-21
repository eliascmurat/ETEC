using Android.App;
using Android.OS;
using Android.Support.V7.App;
using Android.Runtime;
using Android.Widget;
using Android.Gms.Maps;
using Android.Gms.Maps.Model;
using Android.Locations;
using Android.Content;
using Android.Util;
using Android.Support.V4.Content;
using Android;
using Android.Content.PM;
using Android.Support.V4.App;
using System;
using Android.Support.Design.Widget;

namespace PrjLogin
{
    [Activity(Label = "MapActivity", Theme = "@style/Theme.AppCompat.NoActionBar")]
    public class MapActivity : AppCompatActivity, IOnMapReadyCallback, ILocationListener, ActivityCompat.IOnRequestPermissionsResultCallback

    {
        private GoogleMap mMap;
        LocationManager locationManager;
        string provider;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            Xamarin.Essentials.Platform.Init(this, savedInstanceState);
            SetContentView(Resource.Layout.activity_map);

            SetUpMap();
            if (ContextCompat.CheckSelfPermission(this, Manifest.Permission.AccessFineLocation) == (int)Permission.Granted)
            {
                AcessaLocal();
            }
            else
            {
                Toast.MakeText(Application.Context, "Sem Permissão!",
                ToastLength.Short).Show();
            }
            if (ContextCompat.CheckSelfPermission(this, Manifest.Permission.AccessFineLocation) != (int)Permission.Granted)
            {
                if
                (ActivityCompat.ShouldShowRequestPermissionRationale(this, Manifest.Permission.AccessFineLocation))
                {
                    AcessaLocal();
                }
                else
                {
                    ActivityCompat.RequestPermissions(this, new String[] {
                        Manifest.Permission.AccessFineLocation 
                    }, 1000);
                }
            }

        }

        private void SetUpMap() {
            if (mMap == null)
            {
                FragmentManager.FindFragmentById<MapFragment>(Resource.Id.map).GetMapAsync(this);
            }
        }

        public void AcessaLocal()
        {
            locationManager = (LocationManager)GetSystemService(Context.LocationService);
            locationManager.RequestLocationUpdates(LocationManager.GpsProvider, 2000, 1, this);
            
            Criteria locationCriteria = new Criteria();
            locationCriteria.Accuracy = Accuracy.Coarse;
            locationCriteria.PowerRequirement = Power.Medium;
            
            provider = locationManager.GetBestProvider(locationCriteria, true);
            
            if (provider != null)
            {
                locationManager.RequestLocationUpdates(provider, 2000, 1, this);
            }
            else
            {
                Log.Info("RESULT_LOG: ", "No location providers available");
            }

        }

        public void OnMapReady(GoogleMap mMap)
        {
            LatLng location = new LatLng(-23.511302, -47.471458);
            
            CameraPosition.Builder builder = CameraPosition.InvokeBuilder();
            builder.Target(location);
            builder.Zoom(18);
            builder.Bearing(155);
            builder.Tilt(65);

            CameraPosition cameraPosition = builder.Build();
            CameraUpdate cameraUpdate =
            CameraUpdateFactory.NewCameraPosition(cameraPosition);

            mMap.MapType = GoogleMap.MapTypeNormal;
            mMap.UiSettings.ZoomControlsEnabled = true;
            mMap.UiSettings.CompassEnabled = true;
            mMap.MoveCamera(CameraUpdateFactory.ZoomIn());
            mMap.MoveCamera(cameraUpdate);
            
            MarkerOptions markerOpt1 = new MarkerOptions();
            
            markerOpt1.SetPosition(new LatLng(-23.511302, -47.471458));
            markerOpt1.SetTitle("ETEC Fernando Prestes");
            mMap.AddMarker(markerOpt1);
        }
        public override void OnRequestPermissionsResult(int requestCode, string[] permissions, Permission[] grantResults)
        {
            if (requestCode == 0)
            {
                // Received permission result for camera permission.
                Log.Info("RESULT_LOG: ", "Received response for Location permission request.");
            }
            else
            {
                base.OnRequestPermissionsResult(requestCode, permissions,
                grantResults);
            }
        }
        protected override void OnResume()
        {
            base.OnResume();
            if (provider != null)
                locationManager.RequestLocationUpdates(provider, 2000, 1, this);
        }
        protected override void OnPause()
        {
            base.OnPause();
            if (provider != null)
                locationManager.RemoveUpdates(this);
        }

        public void OnLocationChanged(Location location)
        {
            AcessaLocal();
        }

        public void OnProviderDisabled(string provider)
        {
            //throw new NotImplementedException();
        }

        public void OnProviderEnabled(string provider)
        {
            //throw new NotImplementedException();
        }

        public void OnStatusChanged(string provider, [GeneratedEnum] Availability status, Bundle extras)
        {
            //throw new NotImplementedException();
        }
    }
}